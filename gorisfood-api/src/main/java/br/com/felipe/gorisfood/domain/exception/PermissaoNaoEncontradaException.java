package br.com.felipe.gorisfood.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PermissaoNaoEncontradaException(String message) {
		super(message);
	}
	
	public PermissaoNaoEncontradaException(Long permissaoId) {
		this(String.format("Não existe um cadastro de permissão com o código %d.", permissaoId));
	}

	public PermissaoNaoEncontradaException(Long permissaoId, Long grupoId) {
		this(String.format("Não existe um cadastro de permissao com o código %d para o grupo de códido %d.", permissaoId, grupoId));
	}
	
}
