package br.com.felipe.gorisfood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Problema")
@Builder
@Getter
public class Problema {

	@Schema(example = "400")
	private Integer status;

	@Schema(example = "https://gorisfood.com.br/dados-invalidos")
	private String tipo;

	@Schema(example = "Dados inválidos")
	private String titulo;

	@Schema(example = "Um ou mais campos est~çao inválidos. Faça o preenchimento correto e tente novamente.")
	private String detalhe;

	@Schema(example = "Um ou mais campos est~çao inválidos. Faça o preenchimento correto e tente novamente.")
	private String mensagemUsuario;

	@Schema(example = "2023-09-10T13:20:26.902245498Z")
	@JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ssXXX")
	private OffsetDateTime timestamp;

	@Schema(description = "Lista de objetos ou campos que geraram o erro")
	private List<Objeto> objetos;

	@Schema(name = "ObjetoProblema")
	@Builder
	@Getter
	public static class Objeto {

		@Schema(example = "preco")
		private String nome;

		@Schema(example = "O preço é inválido")
		private String erro;
	}
}
