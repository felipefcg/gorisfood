package br.com.felipe.gorisfood.domain.exception;

public class SenhaInvalidaExcpetion extends NegocioException {

	private static final long serialVersionUID = 1L;

	public SenhaInvalidaExcpetion(String message) {
		super(message);
	}
}
