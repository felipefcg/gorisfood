package br.com.felipe.gorisfood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "usuario")
@Getter
@Setter
public class UsuarioResponseDTO extends RepresentationModel<UsuarioResponseDTO> {

	private Long id; 
	

	private String nome;
	

	private String email;
}
