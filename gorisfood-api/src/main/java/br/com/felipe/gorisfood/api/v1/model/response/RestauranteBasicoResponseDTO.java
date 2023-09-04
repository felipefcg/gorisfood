package br.com.felipe.gorisfood.api.v1.model.response;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteBasicoResponseDTO extends RepresentationModel<RestauranteBasicoResponseDTO> {
	

	private Long id;
	

	private String nome;
	

	private BigDecimal taxaFrete;
	
	private CozinhaResponseDTO cozinha;
	
}
