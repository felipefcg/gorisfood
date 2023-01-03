package br.com.felipe.gorisfood.api.model.output;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteOutputDTO {
		private Long id;
		private String nome;
		private BigDecimal taxaFrete;
		private CozinhaOutputDTO cozinha;
	
}
