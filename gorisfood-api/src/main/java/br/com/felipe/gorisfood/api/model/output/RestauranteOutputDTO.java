package br.com.felipe.gorisfood.api.model.output;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record RestauranteOutputDTO(
		Long id,
		String nome,
		BigDecimal taxaFrete,
		CozinhaOutputDTO cozinha) {
	
}
