package br.com.felipe.gorisfood.api.v1.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaIdRequestDTO {
	

	@NotNull
	private Long id;
}
