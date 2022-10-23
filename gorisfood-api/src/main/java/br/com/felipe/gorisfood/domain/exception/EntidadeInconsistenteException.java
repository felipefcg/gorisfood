package br.com.felipe.gorisfood.domain.exception;

public class EntidadeInconsistenteException extends RuntimeException {

	private static final long serialVersionUID = -6948015258549077714L;

	public EntidadeInconsistenteException(String message) {
		super(message);
	}
}
