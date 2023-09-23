package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.v1.model.response.UsuarioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Restaurantes")
@SecurityRequirement(name = "security_auth")public interface RestauranteUsuarioResponsavelControllerOpenApi {
	
	@Operation(summary = "Lista os usuários responsáveis associados ao restaurante",
			responses = {
					@ApiResponse(responseCode = "400", description = "ID do restaurante inválido", 
							 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
							 					schema = @Schema(implementation = Problema.class))),
					@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
					 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
					 		 					schema = @Schema(implementation = Problema.class))})
			})
	CollectionModel<UsuarioResponseDTO> listar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId);
	
	@Operation(summary = "Associação de restaurante com usuário responsável",
			responses = {
					@ApiResponse(responseCode = "204", description = "Associação efetuada"),
					@ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrado", 
			 		 			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			 		 					schema = @Schema(implementation = Problema.class))})
			})
	ResponseEntity<Void> associar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId);
	
	@Operation(summary = "Desssociação de restaurante com usuário responsável",
			responses = {
					@ApiResponse(responseCode = "204", description = "Desassociação efetuada"),
					@ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrado", 
			 		 			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			 		 					schema = @Schema(implementation = Problema.class))})
			})
	ResponseEntity<Void> desassociar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId);
}
