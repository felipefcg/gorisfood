package br.com.felipe.gorisfood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 8136922097807106367L;

	public CidadeNaoEncontradaException(String message) {
		super(message);
	}
	
	public CidadeNaoEncontradaException(Long idCidade) {
		this(String.format("Cidade com id %d não encontrada", idCidade));
	}

}
