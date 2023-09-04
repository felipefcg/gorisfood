package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.v1.model.response.PermissaoResponseDTO;


public interface GrupoPermissaoControllerOpenApi {
	CollectionModel<PermissaoResponseDTO> listar(Long grupoId);
	ResponseEntity<Void> associar (Long grupoId, Long permissaoId);
	ResponseEntity<Void> desassociar (Long grupoId, Long permissaoId);
}
