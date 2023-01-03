package br.com.felipe.gorisfood.api.model.input;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteInputDTO {
		private String nome;
		private BigDecimal taxaFrete;
		private CozinhaIdInputDTO cozinha;
	
}
