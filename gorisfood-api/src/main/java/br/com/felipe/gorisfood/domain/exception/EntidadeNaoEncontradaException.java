package br.com.felipe.gorisfood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = -3902240890112533227L;

	public EntidadeNaoEncontradaException(String message) {
		super(message);
	}
}
