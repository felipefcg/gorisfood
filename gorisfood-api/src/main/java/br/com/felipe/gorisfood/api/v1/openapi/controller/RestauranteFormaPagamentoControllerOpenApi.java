package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.v1.model.response.FormaPagamentoResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {
	
	@ApiOperation("Lista as formas de pagamentos associadas ao restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID do restaurante inválido", 
				 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
				 					schema = @Schema(implementation = Problema.class))),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
		 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
		 		 					schema = @Schema(implementation = Problema.class))})
	})
	CollectionModel<FormaPagamentoResponseDTO> listar(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId);
	
	@ApiOperation("Associação de restaurante com forma de pagamento")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Associação efetuada"),
		@ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrado", 
		 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
		 		 					schema = @Schema(implementation = Problema.class))})
	})
	ResponseEntity<Void> associar(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId, 
						 @ApiParam(value = "ID da forma de pagamento", required = true) Long formaPagamentoId);

	@ApiOperation("Desassociação de restaurante com forma de pagamento")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Desassociação efetuada"),
		@ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encontrado", 
		 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
		 		 					schema = @Schema(implementation = Problema.class))})
	})
	ResponseEntity<Void> desassociar(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId, 
							@ApiParam(value = "ID da forma de pagamento", required = true) Long formaPagamentoId);
}