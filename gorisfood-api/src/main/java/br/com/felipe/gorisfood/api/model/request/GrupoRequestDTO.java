package br.com.felipe.gorisfood.api.model.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoRequestDTO {
	
	@ApiModelProperty(example = "Gerente", required = true)
	@NotBlank
	private String nome;
}
