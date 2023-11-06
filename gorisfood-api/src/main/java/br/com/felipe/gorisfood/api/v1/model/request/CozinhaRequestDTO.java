package br.com.felipe.gorisfood.api.v1.model.request;

import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaRequestDTO {
	
	@Schema(example = "Brasileira", requiredMode = RequiredMode.REQUIRED)
	@NotBlank
	private String nome;
}
