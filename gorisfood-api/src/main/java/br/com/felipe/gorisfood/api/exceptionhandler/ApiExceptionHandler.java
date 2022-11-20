package br.com.felipe.gorisfood.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.felipe.gorisfood.domain.exception.EntidadeEmUsoExcpetion;
import br.com.felipe.gorisfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.EntidadeRelacionamentoNaoEncontradaException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Problema handleCidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {
		return Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(e.getMessage())
				.build();
	}
	
	@ExceptionHandler(EntidadeEmUsoExcpetion.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public Problema handleEntidadeEmUsoExcpetion(EntidadeEmUsoExcpetion e) {
		return Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(e.getMessage())
				.build();
	}
	
	@ExceptionHandler(EntidadeRelacionamentoNaoEncontradaException.class)
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public Problema handleEntidadeRelacionamentoNaoEncontradaException(EntidadeRelacionamentoNaoEncontradaException e) {
		return Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(e.getMessage())
				.build();
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Problema body = Problema.builder()
							.dataHora(LocalDateTime.now())
							.mensagem(String.format("O tipo de mídia %s não é aceito", ex.getContentType()))
							.build();
		return handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Problema body = Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(String.format("O método %s não é suportado para essa URI", ex.getMethod()))
				.build();
		return handleExceptionInternal(ex, body, headers, status, request);
	}
}