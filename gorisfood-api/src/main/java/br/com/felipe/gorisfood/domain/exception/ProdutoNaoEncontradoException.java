package br.com.felipe.gorisfood.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -6793367111650614498L;

	public ProdutoNaoEncontradoException(String message) {
		super(message);
	}
	
	public ProdutoNaoEncontradoException(Long idProduto) {
		this(String.format("Produto não encontrado com o id %d.", idProduto));
	}
}
