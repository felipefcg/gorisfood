package br.com.felipe.gorisfood.api.openapi.controller;

import java.util.List;

import org.springframework.http.MediaType;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.model.response.GrupoResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Usuários")
public interface UsuarioGrupoControllerOpenApi {

	@ApiOperation("Lista os grupos associados a um usuário")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID do usuário inválido",
					 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							 			schema = @Schema(implementation = Problema.class))
		),
		@ApiResponse(responseCode = "404", description = "Usuário não encontrado",
		 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				 			schema = @Schema(implementation = Problema.class))
		)
	})
	List<GrupoResponseDTO> listar(@ApiParam(value = "ID do usuário", required = true) Long usuarioId);

	@ApiOperation("Associação de gurpos com usuário")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Associação realizada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", 
					content = {@Content(schema = @Schema(implementation = Problema.class), 
							   mediaType = MediaType.APPLICATION_JSON_VALUE)}
		)
	})
	void adicionar(@ApiParam(value = "ID do usuário", required = true) Long usuarioId, @ApiParam(value = "ID do grupo", required = true) Long grupoId);
	
	@ApiOperation("Desassociação de grupos com usuário")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", 
					content = {@Content(schema = @Schema(implementation = Problema.class), 
							   mediaType = MediaType.APPLICATION_JSON_VALUE)}
		)
	})
	void remover(@ApiParam(value = "ID do usuário", required = true) Long usuarioId, @ApiParam(value = "ID do grupo", required = true) Long grupoId);
	
}
