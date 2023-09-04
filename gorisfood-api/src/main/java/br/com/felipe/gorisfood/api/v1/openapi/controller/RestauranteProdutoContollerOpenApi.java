package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import br.com.felipe.gorisfood.api.v1.model.request.ProdutoRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.ProdutoResponseDTO;


public interface RestauranteProdutoContollerOpenApi {
	CollectionModel<ProdutoResponseDTO> listar(Long restauranteId, Boolean incluirInativos);
	ProdutoResponseDTO buscar(Long restauranteId, Long produtoId);
	ProdutoResponseDTO criar(Long restauranteId, ProdutoRequestDTO produtoDTO);
	ProdutoResponseDTO alterar(Long restauranteId, Long produtoId, ProdutoRequestDTO produtoDTO);
	void remover(Long restauranteId, Long produtoId); 
}
