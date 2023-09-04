package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import br.com.felipe.gorisfood.api.v1.model.response.PermissaoResponseDTO;



public interface PermissoesControllerOpenApi {
	CollectionModel<PermissaoResponseDTO> listar();
}
