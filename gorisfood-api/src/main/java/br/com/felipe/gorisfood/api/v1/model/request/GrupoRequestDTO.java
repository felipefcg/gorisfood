package br.com.felipe.gorisfood.api.v1.model.request;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoRequestDTO {
	

	@NotBlank
	private String nome;
}
