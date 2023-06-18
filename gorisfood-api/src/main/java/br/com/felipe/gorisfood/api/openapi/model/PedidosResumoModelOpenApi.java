package br.com.felipe.gorisfood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Link;

import br.com.felipe.gorisfood.api.model.response.PedidoResponseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("PedidosResumoModel")
@Data
public class PedidosResumoModelOpenApi {

	private PedidosResumoEmbeddedModelOpenApi _embedded;
	private Link _links;
	private PageModelOpenApi page;
	
	@ApiModel("PedidosResumoEmbeddedModel")
	@Data
	public class PedidosResumoEmbeddedModelOpenApi {	
		private List<PedidoResponseDTO> pedidos;
	}
}
