package br.com.felipe.gorisfood.api.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Relation(collectionRelation = "estados")
@Data
public class EstadoResponseDTO  extends RepresentationModel<EstadoResponseDTO>{
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "MG")
	private String uf;
	
	@ApiModelProperty(example = "Minas Gerais")
	private String nome;
}
