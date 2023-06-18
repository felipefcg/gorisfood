package br.com.felipe.gorisfood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Link;

import br.com.felipe.gorisfood.api.model.response.EstadoResponseDTO;
import br.com.felipe.gorisfood.api.model.response.FormaPagamentoResponseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("FormasPagamentoModelOpenApi")
@Data
public class FormasPagamentoModelOpenApi {

	private FormasPagamentoEmbeddedModelOpenApi _embedded;
	private Link _links;
	
	@ApiModel("FormasPagamentoEmbeddedModel")
	@Data
	public class FormasPagamentoEmbeddedModelOpenApi {	
		private List<FormaPagamentoResponseDTO> formasPagamento;
	}
}
