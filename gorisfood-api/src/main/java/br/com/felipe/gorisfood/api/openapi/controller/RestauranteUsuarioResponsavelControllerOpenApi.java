package br.com.felipe.gorisfood.api.openapi.controller;

import java.util.List;

import org.springframework.http.MediaType;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.model.response.UsuarioResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteUsuarioResponsavelControllerOpenApi {

	@ApiOperation("Lista os usuários responsáveis associados ao restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID do restaurante inválido", 
				 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
				 					schema = @Schema(implementation = Problema.class))),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
		 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
		 		 					schema = @Schema(implementation = Problema.class))})
	})
	public List<UsuarioResponseDTO> listar(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId);
	
	@ApiOperation("Associação de restaurante com usuário responsável")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Associação efetuada"),
		@ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrado", 
		 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
		 		 					schema = @Schema(implementation = Problema.class))})
	})
	public void associar(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId, 
						 @ApiParam(value = "ID do usuário", required = true) Long usuarioId);
	
	@ApiOperation("Desssociação de restaurante com usuário responsável")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Desassociação efetuada"),
		@ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrado", 
		 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
		 		 					schema = @Schema(implementation = Problema.class))})
	})
	public void desassociar(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId, 
							@ApiParam(value = "ID do usuário", required = true)Long usuarioId);
}
