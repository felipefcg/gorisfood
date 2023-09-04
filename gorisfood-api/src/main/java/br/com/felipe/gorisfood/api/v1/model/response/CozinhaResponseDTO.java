package br.com.felipe.gorisfood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cozinhas")
@Getter
@Setter
public class CozinhaResponseDTO extends RepresentationModel<CozinhaResponseDTO> {
	

//	@JsonView(RestauranteView.Resumo.class)
	private Long id;
	

//	@JsonView(RestauranteView.Resumo.class)
	private String nome;
}
