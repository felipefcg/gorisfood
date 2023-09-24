package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.v1.model.request.CidadeRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.CidadeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {
	
	@Operation(summary = "Lista as cidades")
	CollectionModel<CidadeResponseDTO> listar();

	@Operation(summary = "Busca uma cidade por Id", 
				responses = {
						@ApiResponse(responseCode = "200"),
						@ApiResponse(responseCode = "400", description = "ID da cidade inválido", 
							content = @Content(
										schema = @Schema(implementation = Problema.class), 
										mediaType = MediaType.APPLICATION_JSON_VALUE)
						),
						@ApiResponse(responseCode = "404", description = "Cidade não encontrada", 
						 	content = {@Content(schema = @Schema(implementation = Problema.class), 
						 					 mediaType = MediaType.APPLICATION_JSON_VALUE)}
			)
				})
	CidadeResponseDTO buscar(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long id);
	
	@Operation(summary = "Cadastra uma cidade", 
			description = "Cadastro de uma cidade, necessita de um estado e um nome válido",
			responses = {
					@ApiResponse(responseCode = "201", description = "Cidade cadastrada"),
					@ApiResponse(responseCode = "422", description = "ID do estado não cadastrado", 
								 content = {@Content(schema = @Schema(implementation = Problema.class), 
								 					 mediaType = MediaType.APPLICATION_JSON_VALUE)}
					)
			})
	CidadeResponseDTO criar(@RequestBody(description = "Representação de uma nova cidade", required = true ) CidadeRequestDTO cidadeDTO);
	
	@Operation(summary = "Atualiza uma cidade",
			responses = {
					@ApiResponse(responseCode = "200", description = "Cidade atualizada"),
					@ApiResponse(responseCode = "404", description = "Cidade não encontrada", 
								content = {@Content(schema = @Schema(implementation = Problema.class), 
										   mediaType = MediaType.APPLICATION_JSON_VALUE)}
					),
					@ApiResponse(responseCode = "422", description = "ID do estado não cadastrado", 
								 content = {@Content(schema = @Schema(implementation = Problema.class), 
								 					 mediaType = MediaType.APPLICATION_JSON_VALUE)}
					)
			})
	CidadeResponseDTO alterar(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long id, 
			@RequestBody(description = "Representação de uma nova cidade", required = true ) CidadeRequestDTO cidadeDTO);
	
	
	@Operation(summary = "Exclui uma cidade",
			responses = {
					@ApiResponse(responseCode = "204", description = "Cidade excluida"),
					@ApiResponse(responseCode = "404", description = "Cidade não encontrada", 
								 content = {@Content(schema = @Schema(implementation = Problema.class), 
								 mediaType = MediaType.APPLICATION_JSON_VALUE)})
						
			})
	void remover(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long id);
}
