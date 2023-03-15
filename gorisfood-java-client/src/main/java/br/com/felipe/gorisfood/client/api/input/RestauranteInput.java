package br.com.felipe.gorisfood.client.api.input;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RestauranteInput {
	
	private String nome;
	private BigDecimal taxaFrete;
	private CozinhaInput cozinha;
	private EnderecoInput endereco;
}
