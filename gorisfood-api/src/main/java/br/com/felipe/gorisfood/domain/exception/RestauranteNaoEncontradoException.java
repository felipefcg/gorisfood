package br.com.felipe.gorisfood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -434626204674168032L;

	public RestauranteNaoEncontradoException(String message) {
		super(message);
	}
	
	public RestauranteNaoEncontradoException(Long idRestaurante) {
		this(String.format("Não existe um cadastro de restaurante com o código %d.", idRestaurante));
	}

}
