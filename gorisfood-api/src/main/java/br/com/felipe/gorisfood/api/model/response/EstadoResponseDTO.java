package br.com.felipe.gorisfood.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EstadoResponseDTO {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "MG")
	private String uf;
	
	@ApiModelProperty(example = "Minas Gerais")
	private String nome;
}
