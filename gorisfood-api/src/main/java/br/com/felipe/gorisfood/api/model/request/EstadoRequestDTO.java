package br.com.felipe.gorisfood.api.model.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoRequestDTO {
	
	@ApiModelProperty(example = "SP", required = true)
	@NotBlank
	private String uf;
	
	@ApiModelProperty(example = "São Paulo", required = true)
	@NotBlank
	private String nome;
}
