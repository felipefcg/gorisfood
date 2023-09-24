package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import br.com.felipe.gorisfood.api.v1.model.response.PermissaoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Permissões")
@SecurityRequirement(name = "security_auth")public interface PermissoesControllerOpenApi {
	
	@Operation(summary = "Lista as permissões")
	CollectionModel<PermissaoResponseDTO> listar();
}
