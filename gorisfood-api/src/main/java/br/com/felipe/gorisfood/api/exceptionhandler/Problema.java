package br.com.felipe.gorisfood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
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

	@JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ssXXX")
	private OffsetDateTime timestamp;

	private List<Objeto> objetos;

	@Builder
	@Getter
	public static class Objeto {

		private String nome;

		private String erro;
	}
}
