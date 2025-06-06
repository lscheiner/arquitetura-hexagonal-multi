package br.com.scheiner.api.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.scheiner.domain.exception.UsuarioNaoEncontradoException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(UsuarioNaoEncontradoException.class)
	public ResponseEntity<String> notFoundException(UsuarioNaoEncontradoException exception) {

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
