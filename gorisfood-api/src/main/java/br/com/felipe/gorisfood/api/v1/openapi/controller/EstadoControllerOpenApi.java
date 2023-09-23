package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.v1.model.request.EstadoRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.EstadoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Estados")
@SecurityRequirement(name = "security_auth")public interface EstadoControllerOpenApi {
	
	@Operation(summary = "Lista os estados",
			responses = {
					
			})
	CollectionModel<EstadoResponseDTO> listar();
	
	@Operation(summary = "Busca um estado por ID",
			responses = {
					@ApiResponse(responseCode = "400", description = "ID do estado inválido", 
							 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
									 			schema = @Schema(implementation = Problema.class))),
					@ApiResponse(responseCode = "404", description = "Estado não encontrado", 
							 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
					 							schema = @Schema(implementation = Problema.class)))
			})
	EstadoResponseDTO buscar(@Parameter(description = "ID do estado", example = "1") Long id);
	
	@Operation(summary = "Cadastra um estado",
			responses = {
					@ApiResponse(responseCode = "201", description = "Estado cadastrado")
			})
	EstadoResponseDTO criar(@RequestBody(description = "Dados do estado") EstadoRequestDTO estadoDTO);
	
	@Operation(summary = "Atualiza um estado por ID",
			responses = {
					@ApiResponse(responseCode = "200", description = "Estado atualizado")
			})
	EstadoResponseDTO alterar(@Parameter(description = "ID do estado", example = "1") Long id, 
			@RequestBody(description = "Dados atualizados do estado") EstadoRequestDTO estadoDTO);
	
	@Operation(summary = "Exclui um estado por ID",
			responses = {
					@ApiResponse(responseCode = "204", description = "Estado excluido")
			})
	void remover(@Parameter(description = "ID do estado", example = "1") Long id);
}
