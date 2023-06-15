package br.com.felipe.gorisfood.api.model.response;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoResponseDTO extends RepresentationModel<GrupoResponseDTO> {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Gerente")
	private String nome;
}
