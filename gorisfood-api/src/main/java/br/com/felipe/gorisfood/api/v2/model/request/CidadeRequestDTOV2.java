package br.com.felipe.gorisfood.api.v2.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeRequestDTOV2 {

	@ApiModelProperty(example = "Uberlândia", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long idEstado;
}
