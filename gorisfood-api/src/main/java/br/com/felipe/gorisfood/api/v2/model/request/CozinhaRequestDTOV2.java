package br.com.felipe.gorisfood.api.v2.model.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CozinhaRequest")
@Getter
@Setter
public class CozinhaRequestDTOV2 {
	
	@ApiModelProperty(example = "Brasileira", required = true)
	@NotBlank
	private String nome;
}
