package br.com.felipe.gorisfood.api.v1.openapi.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import br.com.felipe.gorisfood.api.v1.model.request.FotoProtudoRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.FotoProdutoReponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
							})
			})
	FotoProdutoReponseDTO buscar(Long restauranteId, Long produtoId);
	
	@Operation(hidden = true)
	ResponseEntity<?> buscarInputStream(Long restauranteId, Long produtoId, 
	   		String acceptHeader) throws HttpMediaTypeNotAcceptableException ;
	FotoProdutoReponseDTO alterarFoto(Long restauranteId, Long produtoId, 
			 FotoProtudoRequestDTO fotoProduto, MultipartFile arquivo) throws IOException;
	void remover(Long restauranteId, Long produtoId);
}
