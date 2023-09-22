package br.com.felipe.gorisfood.api.v1.model.response;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteBasicoResponseDTO extends RepresentationModel<RestauranteBasicoResponseDTO> {
	
	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Thai Gourmet")
	private String nome;
	
	@Schema(example = "19.99")
	private BigDecimal taxaFrete;
	
	@Schema(description = "Dados do tipo de cozinha do restaurante")
	private CozinhaResponseDTO cozinha;
	
}
