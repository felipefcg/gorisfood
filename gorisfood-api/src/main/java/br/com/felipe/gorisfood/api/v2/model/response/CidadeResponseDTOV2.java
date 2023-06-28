package br.com.felipe.gorisfood.api.v2.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
@Getter
@Setter
public class CidadeResponseDTOV2 extends RepresentationModel<CidadeResponseDTOV2> {
	
	@ApiModelProperty(value = "ID da cidade", example = "1")
	private Long idCidade;
	
	@ApiModelProperty(example = "Uberlândia")
	private String nomeCidade;
	
	@ApiModelProperty(example = "1")
	private Long idEstado;
	
	@ApiModelProperty(example = "Minas Gerais")
	private String nomeEstado;
	
	@ApiModelProperty(example = "MG")
	private String uf;
	
}
