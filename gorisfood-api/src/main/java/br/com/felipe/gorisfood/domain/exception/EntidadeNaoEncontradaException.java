package br.com.felipe.gorisfood.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = -3902240890112533227L;

	public EntidadeNaoEncontradaException(String message) {
		super(message);
	}
}
