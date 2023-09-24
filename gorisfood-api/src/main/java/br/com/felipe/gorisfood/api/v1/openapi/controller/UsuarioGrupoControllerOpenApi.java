package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.v1.model.response.GrupoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuários")
@SecurityRequirement(name = "security_auth")public interface UsuarioGrupoControllerOpenApi {
	
	@Operation(summary = "Lista os grupos associados a um usuário",
			responses = {
					@ApiResponse(responseCode = "400", description = "ID do usuário inválido",
							 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
									 			schema = @Schema(implementation = Problema.class))
				),
					@ApiResponse(responseCode = "404", description = "Usuário não encontrado",
					 		content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							 			schema = @Schema(implementation = Problema.class))
					)
			})
	CollectionModel<GrupoResponseDTO> listar(@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId);

	@Operation(summary = "Associação de gurpos com usuário",
			responses = {
					@ApiResponse(responseCode = "204", description = "Associação realizada com sucesso"),
					@ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", 
								content = {@Content(schema = @Schema(implementation = Problema.class), 
										   mediaType = MediaType.APPLICATION_JSON_VALUE)}
					)
			})
	ResponseEntity<Void> adicionar(
			@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId, 
			@Parameter(description = "ID do grupo", example = "1", required = true) Long grupoId);

	@Operation(summary = "Desassociação de grupos com usuário",
			responses = {
					@ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso"),
					@ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", 
								content = {@Content(schema = @Schema(implementation = Problema.class), 
										   mediaType = MediaType.APPLICATION_JSON_VALUE)}
					)
			})
	ResponseEntity<Void> remover(@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId, 
			@Parameter(description = "ID do grupo", example = "1", required = true) Long grupoId);

}
