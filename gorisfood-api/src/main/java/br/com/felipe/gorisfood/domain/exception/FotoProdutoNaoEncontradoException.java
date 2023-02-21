package br.com.felipe.gorisfood.domain.exception;

public class FotoProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -6793367111650614498L;

	public FotoProdutoNaoEncontradoException(String message) {
		super(message);
	}
	
	public FotoProdutoNaoEncontradoException(Long produtoId, Long restauranteId) {
		this(String.format("Não existes um cadastro de foto do produto com código %d para o restaurante de código %d", produtoId, restauranteId));
	}
}
