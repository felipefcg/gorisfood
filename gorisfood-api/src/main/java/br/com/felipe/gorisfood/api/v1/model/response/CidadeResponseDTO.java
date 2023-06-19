package br.com.felipe.gorisfood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Relation(collectionRelation = "cidades")
@Data
public class CidadeResponseDTO extends RepresentationModel<CidadeResponseDTO> {
	
	@ApiModelProperty(value = "ID da cidade", example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Uberlândia")
	private String nome;
	
	private EstadoResponseDTO estado;
}
