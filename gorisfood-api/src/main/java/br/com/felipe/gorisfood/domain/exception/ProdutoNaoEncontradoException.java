package br.com.felipe.gorisfood.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -6793367111650614498L;

	public ProdutoNaoEncontradoException(String message) {
		super(message);
	}
	
	public ProdutoNaoEncontradoException(Long produtoId, Long restauranteId) {
		this(String.format("Não existe um cadastro de produto com o código %d para o restaurante de códido %d.", produtoId, restauranteId));
	}
}
