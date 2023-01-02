package br.com.felipe.gorisfood.api.model.input;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record RestauranteInputDTO(
		String nome,
		BigDecimal taxaFrete,
		CozinhaIdInputDTO cozinha) {
	
}
