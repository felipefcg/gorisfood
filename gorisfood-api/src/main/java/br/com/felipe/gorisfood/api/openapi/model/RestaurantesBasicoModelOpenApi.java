package br.com.felipe.gorisfood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Link;

import br.com.felipe.gorisfood.api.model.response.RestauranteResponseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("RestaurantesBasicoModel")
@Data
public class RestaurantesBasicoModelOpenApi {

	private RestaurantesBasicoEmbeddedModelOpenApi _embedded;
	private Link _links;
	
	@ApiModel("RestaurantesBasicoEmbeddedModel")
	@Data
	public class RestaurantesBasicoEmbeddedModelOpenApi {	
		private List<RestauranteResponseDTO> restaurantes;
	}
}
