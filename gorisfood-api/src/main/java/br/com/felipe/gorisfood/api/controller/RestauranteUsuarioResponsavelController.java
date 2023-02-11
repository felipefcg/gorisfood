package br.com.felipe.gorisfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.assembler.UsuarioResponseDtoAssembler;
import br.com.felipe.gorisfood.api.model.response.UsuarioResponseDTO;
import br.com.felipe.gorisfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController {

	@Autowired
	private CadastroRestauranteService restauranteService;
	
	@Autowired
	private UsuarioResponseDtoAssembler usuarioAssembler;
	
	@GetMapping
	public List<UsuarioResponseDTO> listar(@PathVariable Long restauranteId) {
		var restaurante = restauranteService.buscar(restauranteId);
		return usuarioAssembler.toDtoList(restaurante.getResponsaveis()); 
	}
	
	@PutMapping("{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		restauranteService.associarUsuarioResponsavel(restauranteId, usuarioId);
	}
	
	@DeleteMapping("{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		restauranteService.desassociarUsuarioResponsavel(restauranteId, usuarioId);
	}
}