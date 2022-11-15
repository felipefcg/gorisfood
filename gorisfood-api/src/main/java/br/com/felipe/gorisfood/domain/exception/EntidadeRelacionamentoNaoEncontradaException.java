package br.com.felipe.gorisfood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class EntidadeRelacionamentoNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = -5571932874811495588L;

	public EntidadeRelacionamentoNaoEncontradaException(String message) {
		super(message);
	}
}
