package br.com.estudos.api.integracoes.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.estudos.api.integracoes.exception.NotFoundException;
import br.com.estudos.api.integracoes.exception.UnprocessableEntityException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private ResponseEntity<ErrorResponse> response(final String message, final HttpServletRequest request, final HttpStatus status, LocalDateTime data){
		return ResponseEntity.status(status).body(new ErrorResponse(message, data, status.value(), request.getRequestURI()));
	}
	
	@ExceptionHandler(UnprocessableEntityException.class)
	public ResponseEntity<ErrorResponse> handlerUnprocessableEntityException(UnprocessableEntityException ex, HttpServletRequest request){
		return response(ex.getMessage(), request, HttpStatus.UNPROCESSABLE_ENTITY, LocalDateTime.now());
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerNotFoundException(NotFoundException ex, HttpServletRequest request){
		return response(ex.getMessage(), request, HttpStatus.NOT_FOUND, LocalDateTime.now());
	}
	
}
