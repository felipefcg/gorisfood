package br.com.felipe.gorisfood.api.v1.model.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoRequestDTO {
	
	@Schema(example = "MG")
	@NotBlank
	private String uf;
	
	@Schema(example = "Minas Gerais")
	@NotBlank
	private String nome;
}
