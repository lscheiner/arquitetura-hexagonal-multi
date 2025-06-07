package br.com.scheiner.core.aspect;

import java.time.temporal.Temporal;
import java.util.Objects;
import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import br.com.scheiner.core.annotation.ValidaRequest;
import br.com.scheiner.core.annotation.ValidarIdentificador;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ValidaRequestAspect {

    private static final ExpressionParser SPEL_PARSER = new SpelExpressionParser();

    @Before("@annotation(validaRequest)")
    public void validar(JoinPoint joinPoint, ValidaRequest validaRequest) {
        var identificador = extrairIdentificador(joinPoint);
        log.info("Identificador extraído: [{}]", identificador);
    }


    private Object extrairIdentificador(JoinPoint joinPoint) {

    	var  signature = (MethodSignature) joinPoint.getSignature();
    	var  method = signature.getMethod();
    	var  args = joinPoint.getArgs();
    	var  parameterAnnotations = method.getParameterAnnotations();

        for (int i = 0; i < parameterAnnotations.length; i++) {
            
        	for (var annotation : parameterAnnotations[i]) {
            	if (annotation instanceof ValidarIdentificador valIdentificador) {
                    
            		Object argument = args[i];
                    String expressao = valIdentificador.value();
                    
                    return (expressao.isEmpty() || isTipoSimples(argument))
                            ? argument
                            : avaliarExpressaoSpEL(argument, expressao);
                }
            }
        }
        
        throw new IllegalArgumentException(
            "Nenhum parametro encontrado com a anotacao @ValidarIdentificador no método: " + method.getName()
        );
    }

    private Object avaliarExpressaoSpEL(Object source, String expressao) {
    	 EvaluationContext context = new StandardEvaluationContext(source);
         return SPEL_PARSER.parseExpression(expressao).getValue(context);
    }

    private boolean isTipoSimples(Object obj) {
        if (Objects.isNull(obj)) {
            return true;
        }
        Class<?> clazz = obj.getClass();
        return clazz.isPrimitive() ||
               clazz == String.class ||
               Number.class.isAssignableFrom(clazz) ||
               clazz == Boolean.class ||
               clazz == Character.class ||
               clazz == UUID.class ||
               Temporal.class.isAssignableFrom(clazz);
    }
}