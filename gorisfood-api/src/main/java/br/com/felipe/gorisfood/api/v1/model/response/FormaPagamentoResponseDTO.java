package br.com.felipe.gorisfood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "formasPagamento")
@Getter
@Setter
public class FormaPagamentoResponseDTO extends RepresentationModel<FormaPagamentoResponseDTO> {
	

	private Long id;
	

	private String descricao;
}
