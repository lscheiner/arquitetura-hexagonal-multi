package br.com.scheiner.core.aspect;
//-parameters
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.scheiner.core.annotation.ValidaRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ValidaRequestAspect {

    @Before("@annotation(validaRequest)")
    public void validar(JoinPoint joinPoint, ValidaRequest validaRequest) {

        Object valorCampo = extrairValorCampo(joinPoint, validaRequest);

        log.info("Valor do campo [{}]: {}", validaRequest.field(), valorCampo);

    }

    private Object extrairValorCampo(JoinPoint joinPoint, ValidaRequest validaRequest) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        String field = validaRequest.field();
        Class<?> clazz = validaRequest.clazz();

        for (int i = 0; i < parameterNames.length; i++) {
            if (parameterNames[i].equals(field)) {
                Object arg = args[i];
                if (arg != null && clazz.isAssignableFrom(arg.getClass())) {
                    return arg;
                }
            }
        }

        for (Object arg : args) {
            if (arg != null && validaRequest.clazz().isAssignableFrom(arg.getClass())) {
                
                var v = processRequestBody(arg, validaRequest.field());
                if (v != null) return v;
            }
        }
        
        return null;
    }
    
    public static Map<String, Object> getParametersMap(final JoinPoint joinPoint) {
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final Map<String, Object> params = getParametersMap(methodSignature, joinPoint.getArgs());
        Optional.ofNullable(joinPoint.getThis()).ifPresent(objThis -> params.put("this", objThis));
        return params;
    }
    
    public static Map<String, Object> getParametersMap(final MethodSignature signature, final Object... args) {
        final String[] parameterNames = signature.getParameterNames();
        final Map<String, Object> params = new HashMap<>();
        params.put("method", signature.getName());
        for (int i = 0; i < Math.max(parameterNames.length, args.length); i++) {
            params.put(parameterNames[i], args[i]);
            params.put(Integer.toString(i), args[i]);
        }
        return params;
    }
    
private String processRequestBody(Object requestBody , String methodName) {
    	
    	ExpressionParser parser = new SpelExpressionParser();
    	EvaluationContext context = new StandardEvaluationContext(requestBody);

    	return parser.parseExpression(methodName).getValue(context,String.class);
    }

    private HttpServletRequest getCurrentHttpRequest() {
        var requestAttributes = RequestContextHolder.getRequestAttributes();

        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
            return servletRequestAttributes.getRequest();
        }
        throw new IllegalStateException("Não foi possível obter HttpServletRequest");
    }
}
