package br.com.felipe.gorisfood.api.v1.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDTO {
	
	@Schema(example = "Fulano de Tal", requiredMode = RequiredMode.REQUIRED)
	@NotBlank
	private String nome;
	
	@Schema(example = "fulado@email.com", requiredMode = RequiredMode.REQUIRED)
	@NotBlank
	@Email
	private String email;
}
