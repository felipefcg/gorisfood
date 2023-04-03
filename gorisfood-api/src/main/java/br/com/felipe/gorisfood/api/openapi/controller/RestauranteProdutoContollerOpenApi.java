package br.com.felipe.gorisfood.api.openapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.model.request.ProdutoRequestDTO;
import br.com.felipe.gorisfood.api.model.response.ProdutoResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Produtos")
public interface RestauranteProdutoContollerOpenApi {
	
	@ApiOperation("Lista os produtos de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID do restaurante inválido", 
				 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
				 					schema = @Schema(implementation = Problema.class))),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
		 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
		 		 					schema = @Schema(implementation = Problema.class))})
	})
	List<ProdutoResponseDTO> listar(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId, 
										  @ApiParam(value = "Indica se deve ou não incluir produtos inativos no resultado da listagem",
												    example = "false", defaultValue = "false") boolean incluirInativos);
	
	@ApiOperation("Busca o produto de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID do restaurante ou do produto inválido", 
				 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
				 					schema = @Schema(implementation = Problema.class))),
		@ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado", 
		 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
		 		 					schema = @Schema(implementation = Problema.class))})
	})
	ProdutoResponseDTO buscar(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId, 
									 @ApiParam(value = "ID do produto", required = true) Long produtoId);
	
	@ApiOperation("Cadastra um produto de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
		 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
		 		 					schema = @Schema(implementation = Problema.class))})
	})
	ProdutoResponseDTO criar(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId, 
									ProdutoRequestDTO produtoDTO);
	
	@ApiOperation("Atualiza um produto de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado", 
		 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
		 		 					schema = @Schema(implementation = Problema.class))})
	})
	ProdutoResponseDTO alterar(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId, 
									  @ApiParam(value = "ID do produto", required = true) Long produtoId, 
									  ProdutoRequestDTO produtoDTO);
	
	@ApiIgnore
	@ApiOperation("Exclui o produto de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Produto excluido com sucesso"),
		@ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado", 
		 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
		 		 					schema = @Schema(implementation = Problema.class))})
	})
	void remover(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId, 
						@ApiParam(value = "ID do produto", required = true) Long produtoId); 
}
