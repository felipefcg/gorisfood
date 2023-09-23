package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.v1.model.request.ProdutoRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.ProdutoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Produtos")
@SecurityRequirement(name = "security_auth")public interface RestauranteProdutoContollerOpenApi {
	
	@Operation(summary = "Lista os produtos de um restaurante",
			responses = {
					@ApiResponse(responseCode = "400", description = "ID do restaurante inválido", 
							 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
							 					schema = @Schema(implementation = Problema.class))),
					@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
					 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
					 		 					schema = @Schema(implementation = Problema.class))})
			})
	CollectionModel<ProdutoResponseDTO> listar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "Indica se deve ou não incluir produtos inativos no resultado da listagem", example = "false") Boolean incluirInativos);
	
	@Operation(summary = "Busca o produto de um restaurante",
			responses = {
					@ApiResponse(responseCode = "400", description = "ID do restaurante ou do produto inválido", 
							 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
							 					schema = @Schema(implementation = Problema.class))),
					@ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado", 
					 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
					 		 					schema = @Schema(implementation = Problema.class))})
			})
	ProdutoResponseDTO buscar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "ID do produto", example = "1", required = true) Long produtoId);
	
	@Operation(summary = "Cadastra um produto de um restaurante",
			responses = {
					@ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
					@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
					 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
					 		 					schema = @Schema(implementation = Problema.class))})
			})
	ProdutoResponseDTO criar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			ProdutoRequestDTO produtoDTO);
	
	@Operation(summary = "Atualiza um produto de um restaurante",
			responses = {
					@ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
					@ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado", 
			 		 			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			 		 					schema = @Schema(implementation = Problema.class))})
			})
	ProdutoResponseDTO alterar(
			@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "ID do produto", example = "1", required = true)Long produtoId, 
			ProdutoRequestDTO produtoDTO);
	
	@Operation(summary = "Exclui o produto de um restaurante",
			responses = {
					@ApiResponse(responseCode = "204", description = "Produto excluido com sucesso"),
					@ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado", 
								content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			 		 					schema = @Schema(implementation = Problema.class))})
			})
	void remover(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "ID do produto", example = "1", required = true) Long produtoId); 
}
