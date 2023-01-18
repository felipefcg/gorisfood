package br.com.felipe.gorisfood.domain.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 8511400097318686273L;

	public FormaPagamentoNaoEncontradaException(String message) {
		super(message);
	}
	
	public FormaPagamentoNaoEncontradaException(Long idFormaPagamento) {
		this(String.format("Forma de Pagamento não encontrada com id %d.", idFormaPagamento));
	}

}
