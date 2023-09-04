package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import br.com.felipe.gorisfood.api.v1.model.request.GrupoRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.GrupoResponseDTO;



public interface GrupoControllerOpenApi {
	CollectionModel<GrupoResponseDTO> listar();
	GrupoResponseDTO buscar(Long id);
	void excluir(Long id);
	GrupoResponseDTO criar(GrupoRequestDTO dto) ;
	GrupoResponseDTO alterar(Long id,  GrupoRequestDTO dto);
}
