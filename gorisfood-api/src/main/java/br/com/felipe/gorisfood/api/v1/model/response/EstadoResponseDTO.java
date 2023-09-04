package br.com.felipe.gorisfood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Data;

@Relation(collectionRelation = "estados")
@Data
public class EstadoResponseDTO  extends RepresentationModel<EstadoResponseDTO>{
	

	private Long id;
	

	private String uf;
	

	private String nome;
}
