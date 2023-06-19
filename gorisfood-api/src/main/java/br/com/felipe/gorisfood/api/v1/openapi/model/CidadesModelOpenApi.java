package br.com.felipe.gorisfood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Link;

import br.com.felipe.gorisfood.api.v1.model.response.CidadeResponseDTO;
import br.com.felipe.gorisfood.api.v1.openapi.model.CozinhasModelOpenApi.CozinhasEmbeddedModelOpenApi;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("CidadesModel")
@Data
public class CidadesModelOpenApi {

	private CozinhasEmbeddedModelOpenApi _embedded;
	private Link _links;
	
	@ApiModel("CidadesEmbeddedModel")
	@Data
	public class CidadesEmbeddedModelOpenApi {	
		private List<CidadeResponseDTO> cidades;
	}
}
