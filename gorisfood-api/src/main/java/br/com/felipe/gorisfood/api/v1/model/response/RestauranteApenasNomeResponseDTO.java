package br.com.felipe.gorisfood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteApenasNomeResponseDTO extends RepresentationModel<RestauranteApenasNomeResponseDTO> {
	
	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Thai Gourmet")
	private String nome;
}
