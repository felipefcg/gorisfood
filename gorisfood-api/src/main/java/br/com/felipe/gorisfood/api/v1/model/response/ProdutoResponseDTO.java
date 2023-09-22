package br.com.felipe.gorisfood.api.v1.model.response;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "produtos")
@Getter
@Setter
public class ProdutoResponseDTO extends RepresentationModel<ProdutoResponseDTO>{

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Porco com molho agridoce")
	private String nome;
	
	@Schema(example = "Deliciosa carne suína ao molho especial")
	private String descricao;
	
	@Schema(example = "109.90")
	private BigDecimal preco;
	
	@Schema(example = "true")
	private Boolean ativo;
	
}
