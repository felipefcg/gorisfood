package br.com.felipe.gorisfood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Link;

import br.com.felipe.gorisfood.api.model.response.EstadoResponseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("EstadosModel")
@Data
public class EstadosModelOpenApi {

	private EstadosEmbeddedModelOpenApi _embedded;
	private Link _links;
	
	@ApiModel("EstadosEmbeddedModel")
	@Data
	public class EstadosEmbeddedModelOpenApi {	
		private List<EstadoResponseDTO> estados;
	}
}
