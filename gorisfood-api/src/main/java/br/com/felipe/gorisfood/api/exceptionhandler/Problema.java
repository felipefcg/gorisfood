package br.com.felipe.gorisfood.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class Problema {
	
	private Integer status;
	private String tipo;
	private String titulo;
	private String detalhe;
	
	private String mensagemUsuario;
	private LocalDateTime timestamp;
	private List<Objeto> objetos;

	@Builder
	@Getter
	public static class Objeto {
		private String nome;
		private String erro;
	}
}
