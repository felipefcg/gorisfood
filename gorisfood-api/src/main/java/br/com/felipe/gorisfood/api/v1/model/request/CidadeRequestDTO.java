package br.com.felipe.gorisfood.api.v1.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeRequestDTO {

	@Schema(example = "1")
	@NotBlank
	private String nome;

	@Valid
	@NotNull
	private EstadoIdRequestDTO estado;
}
