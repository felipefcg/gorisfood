package br.com.felipe.gorisfood.api.v2.openapi.model;

import java.util.List;

import org.springframework.hateoas.Link;

import br.com.felipe.gorisfood.api.v1.model.response.CidadeResponseDTO;
import br.com.felipe.gorisfood.api.v1.openapi.model.PageModelOpenApi;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CozinhasModel")
@Getter
@Setter
public class CozinhasModelOpenApiV2 {

	private CozinhasEmbeddedModelOpenApiV2 _embedded;
	private Link _links;
	private PageModelOpenApi page;
	
	@ApiModel("CozinhasEmdddedModel")
	@Data
	public class CozinhasEmbeddedModelOpenApiV2 {	
		private List<CidadeResponseDTO> cozinhas;
	}

}
