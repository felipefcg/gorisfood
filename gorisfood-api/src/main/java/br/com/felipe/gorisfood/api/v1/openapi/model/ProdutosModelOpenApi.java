package br.com.felipe.gorisfood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Link;

import br.com.felipe.gorisfood.api.v1.model.response.ProdutoResponseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("ProdutosModel")
@Data
public class ProdutosModelOpenApi {

	private CidadeEmbeddedModelOpenApi _embedded;
	private Link _links;
	
	@ApiModel("ProdutosEmbeddedModel")
	@Data
	public class CidadeEmbeddedModelOpenApi {	
		private List<ProdutoResponseDTO> produtos;
	}
}
