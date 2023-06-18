package br.com.felipe.gorisfood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Link;

import br.com.felipe.gorisfood.api.model.response.PedidoResponseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("PedidosResumoModel")
@Data
public class PedidosResumoModelOpenApi {

	private PedidoResumoEmbeddedModelOpenApi _embedded;
	private Link _links;
	private PageModelOpenApi page;
	
	@ApiModel("PedidoResumoEmbeddedModel")
	@Data
	public class PedidoResumoEmbeddedModelOpenApi {	
		private List<PedidoResponseDTO> pedidos;
	}
}
