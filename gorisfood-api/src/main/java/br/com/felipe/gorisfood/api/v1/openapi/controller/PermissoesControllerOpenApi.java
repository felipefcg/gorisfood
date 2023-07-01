package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import br.com.felipe.gorisfood.api.v1.model.response.GrupoResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.PermissaoResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Api(tags = "Permissões")
@Tag(name = "Permissões", description = "Gerencia as permissões")
public interface PermissoesControllerOpenApi {
	
	@ApiOperation("Lista as permissões")
	CollectionModel<PermissaoResponseDTO> listar();
}