package br.com.felipe.gorisfood.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.felipe.gorisfood.domain.exception.EntidadeNaoEncontradaException;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Problema handleCidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {
		return Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(e.getMessage())
				.build();
	}
	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	public Problema handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
		
		return Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(String.format("O tipo de mídia %s não é aceito", e.getContentType()))
				.build();
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public Problema handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		
		return Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(String.format("O método %s não é suportado para essa URI", e.getMethod()))
				.build();
	}
	
}