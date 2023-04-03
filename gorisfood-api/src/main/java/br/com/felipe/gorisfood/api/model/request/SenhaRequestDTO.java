package br.com.felipe.gorisfood.api.model.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaRequestDTO {
	
	@ApiModelProperty(example = "1234", required = true)
	@NotBlank
	private String senhaAntiga;
	
	@ApiModelProperty(example = "1234", required = true)
	@NotBlank
	private String senhaNova;
}
