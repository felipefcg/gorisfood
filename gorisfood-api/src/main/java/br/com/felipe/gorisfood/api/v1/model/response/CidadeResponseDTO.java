package br.com.felipe.gorisfood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Data;

@Relation(collectionRelation = "cidades")
@Data
public class CidadeResponseDTO extends RepresentationModel<CidadeResponseDTO> {
	

	private Long id;
	

	private String nome;
	
	private EstadoResponseDTO estado;
}
