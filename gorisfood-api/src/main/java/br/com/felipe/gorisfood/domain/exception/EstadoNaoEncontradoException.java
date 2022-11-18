package br.com.felipe.gorisfood.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -6862527452152532684L;
	
	public EstadoNaoEncontradoException(String message) {
		super(message);
	}

	public EstadoNaoEncontradoException(Long idEstado) {
		this(String.format("Estado não encontrado com o id %d", idEstado));
	}

	
}
