package br.com.felipe.gorisfood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Link;

import br.com.felipe.gorisfood.api.v1.model.response.GrupoResponseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("GruposModel")
@Data
public class GruposModelOpenApi {

	private GruposEmbeddedModelOpenApi _embedded;
	private Link _links;
	
	@ApiModel("GruposEmbeddedModel")
	@Data
	public class GruposEmbeddedModelOpenApi {	
		private List<GrupoResponseDTO> grupos;
	}
}
