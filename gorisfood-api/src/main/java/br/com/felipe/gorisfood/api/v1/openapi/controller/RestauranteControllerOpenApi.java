package br.com.felipe.gorisfood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.v1.model.request.RestauranteRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.RestauranteApenasNomeResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.RestauranteBasicoResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.RestauranteResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Restaurantes")
@SecurityRequirement(name = "security_auth")public interface RestauranteControllerOpenApi {

	@Operation(summary = "Listar restaurantes",
			description = "Busca os dados dos restaurantes cadastrados. "
					+ "Permite utilizar projeção para retornar apenas os nomes dos restaurantes.",
			parameters = {
			@Parameter(name = "projecao",
					description = "Nome da projeção",
					example = "apenas-nome",
					in = ParameterIn.QUERY,
					required = false)
	})
	CollectionModel<RestauranteBasicoResponseDTO> listar();
	
	@Operation(hidden = true)
	CollectionModel<RestauranteApenasNomeResponseDTO> listarApenasNome();
	
	@Operation(summary = "Busca um restaurante por ID",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID do restaurante inválido", 
							 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
							 					schema = @Schema(implementation = Problema.class))),
					@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
						 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
						 		 					schema = @Schema(implementation = Problema.class))})
			})
	RestauranteResponseDTO buscar(@Parameter(description = "ID do restaurante", example = "1") Long id);
	
	@Operation(summary = "Cadastra um restaurante",
			responses = {
					@ApiResponse(responseCode = "201", description = "Restaurante criado"),
					@ApiResponse(responseCode = "422", description = "ID de uma das propriedades (cozinha ou cidade) inexistente no sistema", 
					 			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
					 					schema = @Schema(implementation = Problema.class))})
			})
	RestauranteResponseDTO criar(@RequestBody(description = "Dados do restaurante") RestauranteRequestDTO restauranteDTO);
	
	@Operation(summary = "Atualiza um restaurante por ID",
			responses = {
					@ApiResponse(responseCode = "200", description = "Restaurante atualizado"),
					@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			 		 			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			 		 					schema = @Schema(implementation = Problema.class))}),
					@ApiResponse(responseCode = "422", description = "ID de uma das propriedades (cozinha ou cidade) inexistente no sistema", 
					 			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
					 					schema = @Schema(implementation = Problema.class))})
			})
	RestauranteResponseDTO alterar(@Parameter(description = "ID do restaurante", example = "1") Long id, 
			@RequestBody(description = "Dados do restaurante atualizados") RestauranteRequestDTO restauranteDTO);
	
	@Operation(summary = "Ativa um restaurante por ID",
			responses = {
					@ApiResponse(responseCode = "204", description = "Restaurante ativado"),
					@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			 		 			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			 		 					schema = @Schema(implementation = Problema.class))})
			})
	ResponseEntity<Void> ativar(@Parameter(description = "ID do restaurante", example = "1") Long id);
	
	@Operation(summary = "Inativa um restaurante por ID",
			responses = {
					@ApiResponse(responseCode = "204", description = "Restaurante inativado"),
					@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			 		 			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			 		 					schema = @Schema(implementation = Problema.class))})
			})
	ResponseEntity<Void> inativar(@Parameter(description = "ID do restaurante", example = "1") Long id);
	
	@Operation(summary = "Ativa múltiplos restaurantes",
			responses = {
					@ApiResponse(responseCode = "204", description = "Restaurantes ativados"),
					@ApiResponse(responseCode = "422", description = "ID de restaurante informado inexistente no sistema", 
							 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
							 					schema = @Schema(implementation = Problema.class))})
			})
	ResponseEntity<Void> ativarMultiplos(@Parameter(description = "Lista de IDs de restaurante", example = "1,2,3") List<Long> id);
	
	@Operation(summary = "Inativa múltiplos restaurantes",
			responses = {
					@ApiResponse(responseCode = "204", description = "Restaurantes inativados"),
					@ApiResponse(responseCode = "422", description = "ID de restaurante informado inexistente no sistema", 
							 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
							 					schema = @Schema(implementation = Problema.class))})
			})
	ResponseEntity<Void> inativarMultiplos(@Parameter(description = "Lista de IDs de restaurante", example = "1,2,3") List<Long> id);
	
	@Operation(summary = "Abre um restaurante por ID",
			responses = {
					@ApiResponse(responseCode = "204", description = "Restaurante aberto"),
					@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			 		 			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			 		 					schema = @Schema(implementation = Problema.class))})
			})
	ResponseEntity<Void> abrir(@Parameter(description = "ID do restaurante", example = "1") Long id);
	
	@Operation(summary = "Fecha um restaurante por ID",
			responses = {
					@ApiResponse(responseCode = "204", description = "Restaurante fechado"),
					@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			 		 			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			 		 					schema = @Schema(implementation = Problema.class))})
			})
	ResponseEntity<Void> fechar(@Parameter(description = "ID do restaurante", example = "1") Long id);
}
