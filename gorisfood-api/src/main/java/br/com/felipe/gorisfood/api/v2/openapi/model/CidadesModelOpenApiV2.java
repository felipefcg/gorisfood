package br.com.felipe.gorisfood.api.v2.openapi.model;

import java.util.List;

import org.springframework.hateoas.Link;

import br.com.felipe.gorisfood.api.v2.model.response.CidadeResponseDTOV2;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("CidadesModel")
@Data
public class CidadesModelOpenApiV2 {

	private CidadesEmbeddedModelOpenApiV2 _embedded;
	private Link _links;
	
	@ApiModel("CidadesEmbeddedModel")
	@Data
	public class CidadesEmbeddedModelOpenApiV2 {	
		private List<CidadeResponseDTOV2> cidades;
	}
}
