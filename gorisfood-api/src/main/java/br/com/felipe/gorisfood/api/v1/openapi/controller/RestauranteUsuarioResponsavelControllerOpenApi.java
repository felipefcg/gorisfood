package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.v1.model.response.UsuarioResponseDTO;


public interface RestauranteUsuarioResponsavelControllerOpenApi {
	CollectionModel<UsuarioResponseDTO> listar(Long restauranteId);
	ResponseEntity<Void> associar(Long restauranteId, Long usuarioId);
	ResponseEntity<Void> desassociar(Long restauranteId, Long usuarioId);
}
