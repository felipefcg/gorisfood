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
import br.com.felipe.gorisfood.api.assembler.UsuarioResponseDtoAssembler;
import br.com.felipe.gorisfood.api.model.response.UsuarioResponseDTO;
import br.com.felipe.gorisfood.api.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import br.com.felipe.gorisfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {

	@Autowired
	private CadastroRestauranteService restauranteService;
	
	@Autowired
	private UsuarioResponseDtoAssembler usuarioAssembler;
	
	@Autowired
	private GorisLinks gorisLinks;
	
	@GetMapping
	public CollectionModel<UsuarioResponseDTO> listar(@PathVariable Long restauranteId) {
		var restaurante = restauranteService.buscar(restauranteId);
		 var usuarioCollectionModel = usuarioAssembler.toCollectionModel(restaurante.getResponsaveis())
				.removeLinks()
				.add(gorisLinks.linkToResponsaveisRestaurante(restauranteId))
				.add(gorisLinks.linkToResponsaveisRestauranteAssociar(restauranteId, "associar"));
		 
		 usuarioCollectionModel.getContent()
		 	.forEach( u -> u.add(
		 			gorisLinks.linkToResponsaveisRestauranteDesassociar(restauranteId, u.getId(), "desassociar"))
		 	);
		 	
		 return usuarioCollectionModel;
	}
	
	@PutMapping("{usuarioId}")
	public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		restauranteService.associarUsuarioResponsavel(restauranteId, usuarioId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("{usuarioId}")
	public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		restauranteService.desassociarUsuarioResponsavel(restauranteId, usuarioId);
		return ResponseEntity.noContent().build();
	}
}
