package br.com.felipe.gorisfood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.assembler.FormaPagamentoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.v1.model.response.FormaPagamentoResponseDTO;
import br.com.felipe.gorisfood.api.v1.openapi.controller.RestauranteFormaPagamentoControllerOpenApi;
import br.com.felipe.gorisfood.core.security.AuthUserSecurity;
import br.com.felipe.gorisfood.core.security.CheckSecurity;
import br.com.felipe.gorisfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "v1/restaurantes/{restauranteId}/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {

	@Autowired
	private CadastroRestauranteService restauranteService;
	
	@Autowired
	private FormaPagamentoResponseDtoAssembler formaPagamentoAssembler;
	
	@Autowired
	private GorisLinks gorisLinks;

	@Autowired
	private AuthUserSecurity authUserSecurity;

	@CheckSecurity.Restaurante.PodeConsultar
	@GetMapping
	public CollectionModel<FormaPagamentoResponseDTO> listar(@PathVariable Long restauranteId){
		var formasPagamentoDTO = formaPagamentoAssembler.toCollectionModel(
				restauranteService.listarFormasPagamentoDoRestaurante(restauranteId)
			).removeLinks()
			 .add(gorisLinks.linkToFormasPagamentoRestaurantes(restauranteId));
			 

		if (authUserSecurity.podeGerenciarFuncionamentoRestaurantes(restauranteId)) {
			formasPagamentoDTO
				.add(gorisLinks.linkToFormasPagamentoRestaurantesAssociar(restauranteId, "associar"))
				.getContent().forEach( formaPagamentoDTO -> 
					formaPagamentoDTO.add(gorisLinks.linkToFormasPagamentoRestaurantesDesassociar(restauranteId, formaPagamentoDTO.getId(), "desassociar")
				));
		}
		
		return formasPagamentoDTO;
	}
	
	@CheckSecurity.Restaurante.PodeGerenciarFuncionamento
	@PutMapping("{formaPagamentoId}")
	public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Restaurante.PodeGerenciarFuncionamento
	@DeleteMapping("{formaPagamentoId}")
	public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
		return ResponseEntity.noContent().build();
	}

}
