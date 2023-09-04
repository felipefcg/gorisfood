package br.com.felipe.gorisfood.api.v1.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioComSenhaRequestDTO {
	

	@NotBlank
	private String nome;
	

	@NotBlank
	@Email
	private String email;
	

	@NotBlank
	private String senha;
}
