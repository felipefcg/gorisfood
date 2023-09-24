package br.com.felipe.gorisfood.api.v1.model.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaRequestDTO {
	
	@Schema(example = "1234", requiredMode = RequiredMode.REQUIRED)
	@NotBlank
	private String senhaAntiga;
	
	@Schema(example = "5678", requiredMode = RequiredMode.REQUIRED)
	@NotBlank
	private String senhaNova;
}
