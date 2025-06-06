package br.com.scheiner.core.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.scheiner.core.annotation.ValidaRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ValidaRequestAspect {

	private static final String GETTER_PREFIX = "get";
	
    @Before("@annotation(validaRequest)")
    public void validar(JoinPoint joinPoint, ValidaRequest validaRequest) {
    	
    	HttpServletRequest request = getCurrentHttpRequest();
    	String authorization = request.getHeader("Authorization");
    	log.info("Authorization header: {}", authorization);
        
    	Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        
        Object[] arguments = joinPoint.getArgs();
        
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();

        for (int i = 0; i < parameterAnnotations.length; i++) {
            
        	boolean hasRequestBody = Arrays.stream(parameterAnnotations[i])
                                           .anyMatch(annotation -> annotation.annotationType() == RequestBody.class);

            if (hasRequestBody) {
            	
				var methodName = String.format("%s%s", GETTER_PREFIX, capitalize(validaRequest.value()));
                processRequestBody(arguments[i] , methodName);
            }
        }
    }

    private void processRequestBody(Object requestBody , String methodName) {
        try {
            
        	Method getEmailMethod = requestBody.getClass().getMethod(methodName);
            Object o = getEmailMethod.invoke(requestBody);
           
            log.info("extraído do request: {}", o);
        } catch (Exception e) {
            log.error("Erro ao tentar acessar o método ["+methodName+"]", e);
        }
    }
    
    private String capitalize(String fieldName) {
        if (fieldName == null || fieldName.isEmpty()) return fieldName;
        return Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
    }
    
	private HttpServletRequest getCurrentHttpRequest() {
		var requestAttributes = RequestContextHolder.getRequestAttributes();

		if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
			return servletRequestAttributes.getRequest();
		}
		throw new IllegalStateException("Não foi possível obter HttpServletRequest");
	}
}

