package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.v1.model.response.GrupoResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuários")
@SecurityRequirement(name = "security_auth")public interface UsuarioGrupoControllerOpenApi {
	CollectionModel<GrupoResponseDTO> listar(Long usuarioId);

	ResponseEntity<Void> adicionar(Long usuarioId, Long grupoId);

	ResponseEntity<Void> remover(Long usuarioId, Long grupoId);

}
