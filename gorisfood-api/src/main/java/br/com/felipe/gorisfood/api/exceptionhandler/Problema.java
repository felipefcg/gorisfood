package br.com.felipe.gorisfood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class Problema {
	
	@ApiModelProperty(example = "400", position = 1)
	private Integer status;
	
	@ApiModelProperty(example = "https://api.gorisfood.com.br/dados-invalidos", position = 5)
	private String tipo;
	
	@ApiModelProperty(example = "Dados inválidos", position = 10)
	private String titulo;
	
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", position = 15)
	private String detalhe;
	
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", position = 20)
	private String mensagemUsuario;
	
	@ApiModelProperty(example = "2023-03-24T10:31:52Z", position = 25)
	@JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ssXXX")
	private OffsetDateTime timestamp;
	
	@ApiModelProperty(value = "Lista de objetos ou campos que gerarm o erro (opcional)", position = 30)
	private List<Objeto> objetos;

	@ApiModel("ObjetoProblema")
	@Builder
	@Getter
	public static class Objeto {
		
		@ApiModelProperty(example = "preco", position = 1)
		private String nome;
		
		@ApiModelProperty(example = "O preço é obrigatório", position = 5)
		private String erro;
	}
}
