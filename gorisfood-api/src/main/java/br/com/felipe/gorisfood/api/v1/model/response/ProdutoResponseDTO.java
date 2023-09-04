package br.com.felipe.gorisfood.api.v1.model.response;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "produtos")
@Getter
@Setter
public class ProdutoResponseDTO extends RepresentationModel<ProdutoResponseDTO>{


	private Long id;
	

	private String nome;
	

	private String descricao;
	

	private BigDecimal preco;
	

	private Boolean ativo;
	
}
