package br.com.felipe.gorisfood.domain.exception;

public class EntidadeEmUsoExcpetion extends RuntimeException {
	
	private static final long serialVersionUID = 769000139664658247L;

	public EntidadeEmUsoExcpetion(String message) {
		super(message);
	}
}
