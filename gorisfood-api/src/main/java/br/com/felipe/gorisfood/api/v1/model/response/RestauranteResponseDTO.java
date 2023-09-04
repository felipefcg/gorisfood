package br.com.felipe.gorisfood.api.v1.model.response;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteResponseDTO extends RepresentationModel<RestauranteResponseDTO> {
	

//	@JsonView({
//		RestauranteView.Resumo.class,
//		RestauranteView.ApenasNome.class
//	})
	private Long id;
	

//	@JsonView({
//		RestauranteView.Resumo.class,
//		RestauranteView.ApenasNome.class
//	})
	private String nome;
	

//	@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;
	

	private Boolean ativo;
	

	private Boolean aberto;
	
//	@JsonView(RestauranteView.Resumo.class)
	private CozinhaResponseDTO cozinha;
	private EnderecoResponseDTO endereco;
}
