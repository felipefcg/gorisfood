package br.com.felipe.gorisfood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "formasPagamento")
@Getter
@Setter
public class FormaPagamentoResponseDTO extends RepresentationModel<FormaPagamentoResponseDTO> {
	
	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Dinheiro")
	private String descricao;
}
