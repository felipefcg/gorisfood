package br.com.felipe.gorisfood.api.v1.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeRequestDTO {

	@ApiModelProperty(example = "Uberlândia", required = true)
	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private EstadoIdRequestDTO estado;
}