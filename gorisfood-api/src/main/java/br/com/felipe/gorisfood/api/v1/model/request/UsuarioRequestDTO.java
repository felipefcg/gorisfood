package br.com.felipe.gorisfood.api.v1.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDTO {
	
	@ApiModelProperty(example = "Fulano de Tal", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "fulado@email.com", required = true)
	@NotBlank
	@Email
	private String email;
}
