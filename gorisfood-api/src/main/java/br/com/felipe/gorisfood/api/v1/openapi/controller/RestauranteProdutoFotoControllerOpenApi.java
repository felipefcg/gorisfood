package br.com.felipe.gorisfood.api.v1.openapi.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.v1.model.request.FotoProtudoRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.FotoProdutoReponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Produtos")
@SecurityRequirement(name = "security_auth")public interface RestauranteProdutoFotoControllerOpenApi {
	
	@Operation(summary = "Busca a foto do produto de um restaurante",
			responses = {
					@ApiResponse(responseCode = "200",
							content = {
									@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FotoProdutoReponseDTO.class)),
									@Content(mediaType = MediaType.IMAGE_JPEG_VALUE, schema = @Schema(type = "string", format = "binary")),
									@Content(mediaType = MediaType.IMAGE_PNG_VALUE, schema = @Schema(type = "string", format = "binary"))
							}),
					@ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", 
		 			 		content = {
		 			 				@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problema.class))
		 			 		}),
					@ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado", 
					 		content = {
					 				@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problema.class))
					 		})
			})
	FotoProdutoReponseDTO buscar(
			@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "ID do protduto", example = "1", required = true) Long produtoId);
	
	@Operation(hidden = true)
	ResponseEntity<?> buscarInputStream(
			@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "ID do protduto", example = "1", required = true) Long produtoId, 
			@Parameter(description = "Content Type que deseja o retorno", example = MediaType.IMAGE_JPEG_VALUE, required = true) String acceptHeader) 
					throws HttpMediaTypeNotAcceptableException ;
	
	@Operation(summary = "Atualiza a foto do produto de um restaurante",
			responses = {
					@ApiResponse(responseCode = "200", description = "Foto do produto atualizada"),
					@ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado", 
								content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
		 		 						 schema = @Schema(implementation = Problema.class))})
			})
	FotoProdutoReponseDTO alterarFoto(
			@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "ID do protduto", example = "1", required = true) Long produtoId, 
			@RequestBody(required = true) FotoProtudoRequestDTO fotoProduto) throws IOException;
	
	@Operation(summary = "Exclui a foto do produto de um restaurante",
			responses = {
					@ApiResponse(responseCode = "204", description = "Foto do produto excluido com sucesso"),
					@ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado", 
		 		 	 			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
		 		 						 schema = @Schema(implementation = Problema.class))})
			})
	void remover(
			@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId,
			@Parameter(description = "ID do protduto", example = "1", required = true) Long produtoId);
}
