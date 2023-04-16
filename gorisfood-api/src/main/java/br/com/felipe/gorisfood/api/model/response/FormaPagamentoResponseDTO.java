package br.com.felipe.gorisfood.api.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "formas-pagamento")
@Getter
@Setter
public class FormaPagamentoResponseDTO extends RepresentationModel<FormaPagamentoResponseDTO> {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Dinheiro")
	private String descricao;
}
