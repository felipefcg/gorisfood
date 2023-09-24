package br.com.felipe.gorisfood.api.v1.openapi.controller;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.v1.model.request.CozinhaRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.CozinhaResponseDTO;
import br.com.felipe.gorisfood.core.openapi.PageableParameter;
import br.com.felipe.gorisfood.domain.model.Cozinha;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cozinhas")
@SecurityRequirement(name = "security_auth")public interface CozinhaControllerOpenApi {

	@Operation(summary = "Lista as cozinhas",
			description = "Lista as cozinhas de forma paginada")
	@PageableParameter
	PagedModel<CozinhaResponseDTO> listar (@Parameter(hidden = true) Pageable pagable);
	
	@Operation(summary = "Busca uma cozinha por ID",
			description = "Busca uma cozinha por ID",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID de cozinha no formato inválido",
							 	 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
					 			 schema = @Schema(implementation = Problema.class))),
					@ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
							 	 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
					 			 schema = @Schema(implementation = Problema.class)))
			})
	CozinhaResponseDTO buscar (@Parameter(description = "ID da cozinha", example = "1") Long id);
	
	@Operation(summary = "Cadastra uma cozinha",
			description = "Cadastra uma cozinha",
			responses = {
					@ApiResponse(responseCode = "201", description = "Cozinha Cadastrada")
			})
	CozinhaResponseDTO criar(@RequestBody CozinhaRequestDTO cozinhaDTO);
	
	@Operation(summary = "Atualiza uma cozinha por ID",
			description = "Atualiza uma cozinha por ID",
			responses = {
					@ApiResponse(responseCode = "200", description = "Cozinha atualizada"),
					@ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
					 	content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							 			schema = @Schema(implementation = Problema.class))
					)
			})
	CozinhaResponseDTO alterar(@Parameter(description = "ID da cozinha", example = "1") Long id, 
			@RequestBody CozinhaRequestDTO cozinhaDTO);
	
	@Operation(summary = "Exclui uma cozinha por ID",
			description = "Exclui uma cozinha por ID",
			responses = {
					@ApiResponse(responseCode = "204", description = "Cozinha excluida"),
					@ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
					 	content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							 			schema = @Schema(implementation = Problema.class))
					)
			})
	void remover(@Parameter(description = "ID da cozinha", example = "1") Long id);
	
	@Operation(summary = "Busca a primeira cozinha cadastrada na aplicação - Testes", hidden = true)
	Optional<Cozinha> buscarPrimeiro();
}
