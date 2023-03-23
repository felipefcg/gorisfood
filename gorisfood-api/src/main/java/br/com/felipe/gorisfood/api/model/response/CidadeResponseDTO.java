package br.com.felipe.gorisfood.api.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Data
public class CidadeResponseDTO {
	
//	@ApiModelProperty(value = "ID da cidade", example = "1")
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Uberlândia")
	private String nome;
	
	private EstadoResponseDTO estado;
}
