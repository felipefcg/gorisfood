package br.com.felipe.gorisfood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.GorisLinks;
import br.com.felipe.gorisfood.api.assembler.FormaPagamentoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.model.response.FormaPagamentoResponseDTO;
import br.com.felipe.gorisfood.api.openapi.controller.RestauranteFormaPagamentoControllerOpenApi;
import br.com.felipe.gorisfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "restaurantes/{restauranteId}/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {

	@Autowired
	private CadastroRestauranteService restauranteService;
	
	@Autowired
	private FormaPagamentoResponseDtoAssembler formaPagamentoAssembler;
	
	@Autowired
	private GorisLinks gorisLinks;
	
	@GetMapping
	public CollectionModel<FormaPagamentoResponseDTO> listar(@PathVariable Long restauranteId){
		var formasPagamentoDTO = formaPagamentoAssembler.toCollectionModel(
				restauranteService.listarFormasPagamentoDoRestaurante(restauranteId)
			).removeLinks()
			 .add(gorisLinks.linkToFormasPagamentoRestaurantes(restauranteId));

		formasPagamentoDTO.getContent()
			.forEach( formaPagamentoDTO -> 
				formaPagamentoDTO.add(gorisLinks.linkToFormasPagamentoRestaurantesDesassociar(restauranteId, formaPagamentoDTO.getId(), "desassociar")
			));
							
		return formasPagamentoDTO;
	}
	
	@PutMapping("{formaPagamentoId}")
	public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("{formaPagamentoId}")
	public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
		return ResponseEntity.noContent().build();
	}

}
