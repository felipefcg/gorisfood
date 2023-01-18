package br.com.felipe.gorisfood.api.model.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteResponseDTO {
		private Long id;
		private String nome;
		private BigDecimal taxaFrete;
		private Boolean ativo;
		private CozinhaResponseDTO cozinha;
		private EnderecoResponseDTO endereco;
}
