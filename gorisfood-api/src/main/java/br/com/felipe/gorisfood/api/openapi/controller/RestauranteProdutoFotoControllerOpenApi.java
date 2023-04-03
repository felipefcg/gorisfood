package br.com.felipe.gorisfood.api.openapi.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.model.request.FotoProtudoRequestDTO;
import br.com.felipe.gorisfood.api.model.response.FotoProdutoReponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {

	@ApiOperation(value = "Busca a foto do produto de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK",
					content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
							   @Content(mediaType = MediaType.IMAGE_JPEG_VALUE, schema = @Schema(implementation = Object.class)),
							   @Content(mediaType = MediaType.IMAGE_PNG_VALUE, schema = @Schema(implementation = Object.class))
					}),
		@ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", 
		 			 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
		 					schema = @Schema(implementation = Problema.class))}),
		@ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado", 
		 		   content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
		 		 					schema = @Schema(implementation = Problema.class))})
	})
	public FotoProdutoReponseDTO buscar(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId, 
			 							@ApiParam(value = "ID do produto", required = true) Long produtoId);
	
	@ApiOperation(value = "Busca a foto do produto de um restaurante", hidden = true)
	public ResponseEntity<?> buscarInputStream(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId, 
			 								   @ApiParam(value = "ID do produto", required = true) Long produtoId, 
			 								   @ApiParam(value = "Content Type que deseja o retorno", required = false) String acceptHeader) throws HttpMediaTypeNotAcceptableException ;

	@ApiOperation(value = "Atualiza a foto do produto de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Foto do produto atualizada", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
		@ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado", 
		 		 	 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
		 		 						 schema = @Schema(implementation = Problema.class))})
	})
	public FotoProdutoReponseDTO alterarFoto(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId, 
											 @ApiParam(value = "ID do produto", required = true) Long produtoId, 
											 FotoProtudoRequestDTO fotoProduto) throws IOException;

	@ApiOperation("Exclui a foto do produto de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Foto do produto excluido com sucesso"),
		@ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado", 
		 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
		 		 					schema = @Schema(implementation = Problema.class))})
	})
	public void remover(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId, 
						@ApiParam(value = "ID do produto", required = true) Long produtoId);
}
