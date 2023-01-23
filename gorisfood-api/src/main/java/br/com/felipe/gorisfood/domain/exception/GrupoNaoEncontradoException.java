package br.com.felipe.gorisfood.domain.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public GrupoNaoEncontradoException(String message) {
		super(message);
	}
	
	public GrupoNaoEncontradoException(Long idGrupo) {
		this(String.format("Não existe um cadastro de grupo com o código %d.", idGrupo));
	}
	
	public GrupoNaoEncontradoException(Long grupoId, Long permissaoId) {
		this(String.format("Não existe um cadastro de permissao com o código %d para o grupo de códido %d.", permissaoId, grupoId));
	}

}
