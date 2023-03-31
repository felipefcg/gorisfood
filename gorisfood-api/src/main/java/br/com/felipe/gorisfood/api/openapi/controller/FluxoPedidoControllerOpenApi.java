package br.com.felipe.gorisfood.api.openapi.controller;

import org.springframework.http.MediaType;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {

	@ApiOperation("Confirmação de pedido")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Pedido confirmado pelo restaurante" ),
		@ApiResponse( responseCode = "404", description = "Pedido não encontrado" , 
		  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			  				 schema = @Schema(implementation = Problema.class))
		)
	})
	public void confirmar(@ApiParam(value = "Código do Pedido", 
									example = "1e387510-6710-434b-b761-609e8ca29d31") 
						 String codigoPedido);
	
	@ApiOperation("Registrar entrega de pedido")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Pedido criado" ),
		@ApiResponse( responseCode = "404", description = "Pedido não encontrado" , 
		  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			  				 schema = @Schema(implementation = Problema.class))
		)
	})
	public void entregar(@ApiParam(value = "Código do Pedido", 
								example = "1e387510-6710-434b-b761-609e8ca29d31") 
						 String codigoPedido);
	
	@ApiOperation("Cancelamento de pedido")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Pedido cancelado" ),
		@ApiResponse( responseCode = "404", description = "Pedido não encontrado" , 
		  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			  				 schema = @Schema(implementation = Problema.class))
		)
	})
	public void cancelar(@ApiParam(value = "Código do Pedido", 
								example = "1e387510-6710-434b-b761-609e8ca29d31") 
						 String codigoPedido);
}
