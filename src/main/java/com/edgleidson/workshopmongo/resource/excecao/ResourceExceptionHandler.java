// ResourceExceptionHandler = Manipulador de Exceções de Recursos.
package com.edgleidson.workshopmongo.resource.excecao;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.edgleidson.workshopmongo.servico.excecao.ObjetoNaoEncontradoException;

// @ControllerAdvice = Anotação para tratar possiveis erro nas requisições.
@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<StandardError> objetoNaoEncontrado(ObjetoNaoEncontradoException ex, HttpServletRequest requisicao){
		
		HttpStatus status = HttpStatus.NOT_FOUND; //404.
		
		// new StandardError(timestamp, status, error, message, path).
		StandardError erroPadrao = new StandardError(
				System.currentTimeMillis(), status.value(), "Não encontrado", ex.getMessage(), requisicao.getRequestURI());
		return ResponseEntity.status(status).body(erroPadrao);
	}
}