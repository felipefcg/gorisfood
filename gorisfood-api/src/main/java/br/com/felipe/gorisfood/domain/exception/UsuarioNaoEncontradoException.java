package br.com.felipe.gorisfood.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public UsuarioNaoEncontradoException(String message) {
		super(message);
	}

	public UsuarioNaoEncontradoException(Long idUsuario) {
		this(String.format("Usuário não encontrado com o id %d.", idUsuario));
	}
}
