package br.com.felipe.gorisfood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "grupos")
@Getter
@Setter
public class GrupoResponseDTO extends RepresentationModel<GrupoResponseDTO> {
	

	private Long id;
	

	private String nome;
}
