package br.com.felipe.gorisfood.domain.exception;

public class EntidadeRelacionamentoNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = -5571932874811495588L;

	public EntidadeRelacionamentoNaoEncontradaException(String message) {
		super(message);
	}
}
