package br.com.felipe.gorisfood.api.model.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaRequestDTO {
	
	@ApiModelProperty(example = "Brasileira", required = true)
	@NotBlank
	private String nome;
}
