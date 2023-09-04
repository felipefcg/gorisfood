package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.v1.model.response.GrupoResponseDTO;

public interface UsuarioGrupoControllerOpenApi {
	CollectionModel<GrupoResponseDTO> listar(Long usuarioId);

	ResponseEntity<Void> adicionar(Long usuarioId, Long grupoId);

	ResponseEntity<Void> remover(Long usuarioId, Long grupoId);

}
