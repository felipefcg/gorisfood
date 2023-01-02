package br.com.felipe.gorisfood.api.model.output;

import lombok.Builder;

@Builder
public record CozinhaOutputDTO(
		Long id,
		String nome
		) {

}
