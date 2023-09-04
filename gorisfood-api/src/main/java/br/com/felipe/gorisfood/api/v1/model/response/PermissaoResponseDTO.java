package br.com.felipe.gorisfood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "permissoes")
@Getter
@Setter
public class PermissaoResponseDTO extends RepresentationModel<PermissaoResponseDTO> {


	private Long id;
	

	private String nome;
	

	private String descricao;
}
