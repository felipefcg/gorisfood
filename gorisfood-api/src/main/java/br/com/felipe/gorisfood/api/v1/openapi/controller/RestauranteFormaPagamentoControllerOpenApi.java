package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.v1.model.response.FormaPagamentoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Restaurantes")
@SecurityRequirement(name = "security_auth")public interface RestauranteFormaPagamentoControllerOpenApi {
	
	@Operation(summary = "Lista as formas de pagamentos associadas ao restaurante",
			responses = {
					@ApiResponse(responseCode = "400", description = "ID do restaurante inválido", 
							 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
							 					schema = @Schema(implementation = Problema.class))),
					@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
					 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
					 		 					schema = @Schema(implementation = Problema.class))})
			})
	CollectionModel<FormaPagamentoResponseDTO> listar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId);
	
	@Operation(summary = "Associação de restaurante com forma de pagamento",
			responses = {
					@ApiResponse(responseCode = "204", description = "Associação efetuada")
			})
	ResponseEntity<Void> associar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "ID da forma de pagamento", example = "1", required = true) Long formaPagamentoId);
	
	@Operation(summary = "Desassociação de restaurante com forma de pagamento",
			responses = {
					@ApiResponse(responseCode = "204", description = "Desassociação efetuada")
			})
	ResponseEntity<Void> desassociar(@Parameter(description = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@Parameter(description = "ID da forma de pagamento", example = "1", required = true) Long formaPagamentoId);
}
