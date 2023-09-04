package br.com.felipe.gorisfood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.v1.model.request.RestauranteRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.RestauranteApenasNomeResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.RestauranteBasicoResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.RestauranteResponseDTO;



public interface RestauranteControllerOpenApi {
	CollectionModel<RestauranteBasicoResponseDTO> listar();
	CollectionModel<RestauranteApenasNomeResponseDTO> listarApenasNome();
	RestauranteResponseDTO buscar(Long id);
	RestauranteResponseDTO criar(RestauranteRequestDTO restauranteDTO);
	RestauranteResponseDTO alterar(Long id, RestauranteRequestDTO restauranteDTO);
	ResponseEntity<Void> ativar(Long id);
	ResponseEntity<Void> inativar(Long id);
	ResponseEntity<Void> ativarMultiplos(List<Long> id);
	ResponseEntity<Void> inativarMultiplos(List<Long> id);
	ResponseEntity<Void> abrir(Long id);
	ResponseEntity<Void> fechar(Long id);
}
