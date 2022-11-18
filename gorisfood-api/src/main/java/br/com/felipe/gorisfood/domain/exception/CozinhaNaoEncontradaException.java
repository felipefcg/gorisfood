package br.com.felipe.gorisfood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 8511400097318686273L;

	public CozinhaNaoEncontradaException(String message) {
		super(message);
	}
	
	public CozinhaNaoEncontradaException(Long idCozinha) {
		this(String.format("Cozinha com %d não encontrada", idCozinha));
	}

}
