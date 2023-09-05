package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.v1.model.response.FormaPagamentoResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Restaurantes")
@SecurityRequirement(name = "security_auth")public interface RestauranteFormaPagamentoControllerOpenApi {
	CollectionModel<FormaPagamentoResponseDTO> listar(Long restauranteId);
	ResponseEntity<Void> associar(Long restauranteId, Long formaPagamentoId);
	ResponseEntity<Void> desassociar(Long restauranteId, Long formaPagamentoId);
}
