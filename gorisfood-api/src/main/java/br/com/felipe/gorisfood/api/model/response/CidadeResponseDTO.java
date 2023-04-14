package br.com.felipe.gorisfood.api.model.response;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CidadeResponseDTO extends RepresentationModel<CidadeResponseDTO> {
	
	@ApiModelProperty(value = "ID da cidade", example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Uberlândia")
	private String nome;
	
	private EstadoResponseDTO estado;
}
