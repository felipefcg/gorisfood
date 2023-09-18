package br.com.felipe.gorisfood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cozinhas")
@Getter
@Setter
public class CozinhaResponseDTO extends RepresentationModel<CozinhaResponseDTO> {
	

//	@JsonView(RestauranteView.Resumo.class)
	@Schema(example = "1")
	private Long id;
	

//	@JsonView(RestauranteView.Resumo.class)
	@Schema(example = "Brasileira")
	private String nome;
}
