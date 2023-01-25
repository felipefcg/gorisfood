package br.com.felipe.gorisfood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public PedidoNaoEncontradoException(String message) {
		super(message);
	}

	public PedidoNaoEncontradoException(Long pedidoId) {
		this(String.format("Não existe um pedido com o código %d", pedidoId));
	}
}
