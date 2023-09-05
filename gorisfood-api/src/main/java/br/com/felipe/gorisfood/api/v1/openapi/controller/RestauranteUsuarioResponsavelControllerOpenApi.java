package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.v1.model.response.UsuarioResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Restaurantes")
@SecurityRequirement(name = "security_auth")public interface RestauranteUsuarioResponsavelControllerOpenApi {
	CollectionModel<UsuarioResponseDTO> listar(Long restauranteId);
	ResponseEntity<Void> associar(Long restauranteId, Long usuarioId);
	ResponseEntity<Void> desassociar(Long restauranteId, Long usuarioId);
}
