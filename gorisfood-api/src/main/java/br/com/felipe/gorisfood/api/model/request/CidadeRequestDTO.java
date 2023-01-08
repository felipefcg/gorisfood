package br.com.felipe.gorisfood.api.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeRequestDTO {
	
	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private EstadoIdRequestDTO estado;
}
