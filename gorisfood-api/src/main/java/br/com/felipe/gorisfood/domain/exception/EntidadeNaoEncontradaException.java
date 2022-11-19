package br.com.felipe.gorisfood.domain.exception;

public abstract class EntidadeNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = -3902240890112533227L;

	public EntidadeNaoEncontradaException(String message) {
		super(message);
	}
}
