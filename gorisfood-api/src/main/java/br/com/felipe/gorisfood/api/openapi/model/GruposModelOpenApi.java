package br.com.felipe.gorisfood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Link;

import br.com.felipe.gorisfood.api.model.response.GrupoResponseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("GruposModel")
@Data
public class GruposModelOpenApi {

	private GrupoEmbeddedModelOpenApi _embedded;
	private Link _links;
	
	@ApiModel("GrupoEmbeddedModel")
	@Data
	public class GrupoEmbeddedModelOpenApi {	
		private List<GrupoResponseDTO> grupos;
	}
}
