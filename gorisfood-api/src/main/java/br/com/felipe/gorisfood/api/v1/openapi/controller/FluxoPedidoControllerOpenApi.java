package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pedidos")
@SecurityRequirement(name = "security_auth")public interface FluxoPedidoControllerOpenApi {
	
	@Operation(summary = "",
			responses = {
					@ApiResponse(responseCode = "204", description = "Pedido confirmado pelo restaurante" ),
					@ApiResponse( responseCode = "404", description = "Pedido não encontrado" , 
					  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
						  				 schema = @Schema(implementation = Problema.class))
					)
			})
	public ResponseEntity<Void> confirmar(@Parameter(
			description = "Código hash do pedido", 
			example = "1e387510-6710-434b-b761-609e8ca29d31") String codigoPedido);
	
	@Operation(summary = "",
			responses = {
					@ApiResponse(responseCode = "204", description = "Pedido criado" ),
					@ApiResponse( responseCode = "404", description = "Pedido não encontrado" , 
					  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
						  				 schema = @Schema(implementation = Problema.class))
					)
			})
	public ResponseEntity<Void> entregar(@Parameter(
			description = "Código hash do pedido", 
			example = "1e387510-6710-434b-b761-609e8ca29d31") String codigoPedido);
	
	@Operation(summary = "",
			responses = {
					@ApiResponse(responseCode = "204", description = "Pedido cancelado" ),
					@ApiResponse( responseCode = "404", description = "Pedido não encontrado" , 
					  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
						  				 schema = @Schema(implementation = Problema.class))
					)
			})
	public ResponseEntity<Void> cancelar(@Parameter(
			description = "Código hash do pedido", 
			example = "1e387510-6710-434b-b761-609e8ca29d31") String codigoPedido);
}
