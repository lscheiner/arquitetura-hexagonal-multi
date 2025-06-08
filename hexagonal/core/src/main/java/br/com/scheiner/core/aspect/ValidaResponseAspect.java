package br.com.scheiner.core.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import br.com.scheiner.core.annotation.ValidarResponse;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ValidaResponseAspect {

    private static final ExpressionParser SPEL_PARSER = new SpelExpressionParser();

    @AfterReturning(pointcut = "@annotation(validarResponse)", returning = "returnValue")
    public void validar(JoinPoint joinPoint , Object returnValue, ValidarResponse validarResponse) {

    
    	System.out.println(returnValue);
    	
    }


    private Object extrairIdentificador(JoinPoint joinPoint) {

    	
    	return null;
    }

    private Object avaliarExpressaoSpEL(Object source, String expressao) {
    	 EvaluationContext context = new StandardEvaluationContext(source);
         return SPEL_PARSER.parseExpression(expressao).getValue(context);
    }
}