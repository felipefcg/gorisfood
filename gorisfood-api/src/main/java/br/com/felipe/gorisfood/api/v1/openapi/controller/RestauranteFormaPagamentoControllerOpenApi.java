package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.v1.model.response.FormaPagamentoResponseDTO;


public interface RestauranteFormaPagamentoControllerOpenApi {
	CollectionModel<FormaPagamentoResponseDTO> listar(Long restauranteId);
	ResponseEntity<Void> associar(Long restauranteId, Long formaPagamentoId);
	ResponseEntity<Void> desassociar(Long restauranteId, Long formaPagamentoId);
}
