package br.com.scheiner.core.aspect;

import java.util.Collection;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.scheiner.core.annotation.ValidarResponse;
import br.com.scheiner.core.utils.AspectUtils;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ValidaResponseAspect {

    private static final ExpressionParser SPEL_PARSER = new SpelExpressionParser();

    @AfterReturning(pointcut = "@annotation(validarResponse)", returning = "returnValue")
    public void validar(JoinPoint joinPoint, Object returnValue, ValidarResponse validarResponse) {
        
    	Object corpoResposta = extrairObjetoReal(returnValue);

        if (corpoResposta != null) {
            
        	String expressao = validarResponse.value();
            
            var resp = (expressao.isEmpty() || AspectUtils.isTipoSimples(corpoResposta))
                    ? corpoResposta
                    : avaliarExpressaoSpEL(corpoResposta, expressao);
            
            log.info("Identificador extraído response: [{}]", resp);
 
        	
        }
    }

    private Object extrairObjetoReal(Object retorno) {
        Object resultado = retorno;

        if (retorno instanceof ResponseEntity<?> responseEntity) {
            resultado = responseEntity.getBody();
        }

        if (resultado instanceof Collection<?> colecao && !colecao.isEmpty()) {
            return colecao.iterator().next();
        }

        return resultado;
    }

    private Object avaliarExpressaoSpEL(Object source, String expressao) {
    	 EvaluationContext context = new StandardEvaluationContext(source);
         return SPEL_PARSER.parseExpression(expressao).getValue(context);
    }
}