package br.com.felipe.gorisfood.api.model.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.felipe.gorisfood.api.model.view.RestauranteView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteResponseDTO {
	
	@JsonView({
		RestauranteView.Resumo.class,
		RestauranteView.ApenasNome.class
	})
	private Long id;
	
	@JsonView({
		RestauranteView.Resumo.class,
		RestauranteView.ApenasNome.class
	})
	private String nome;
	
	@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;
	
	private Boolean ativo;
	private Boolean aberto;
	
	@JsonView(RestauranteView.Resumo.class)
	private CozinhaResponseDTO cozinha;
	private EnderecoResponseDTO endereco;
}
