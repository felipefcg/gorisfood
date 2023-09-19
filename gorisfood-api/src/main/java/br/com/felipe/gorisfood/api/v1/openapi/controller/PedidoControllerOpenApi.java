package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.v1.model.request.PedidoRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.PedidoResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.PedidoResumidoResponseDTO;
import br.com.felipe.gorisfood.core.openapi.PageableParameter;
import br.com.felipe.gorisfood.core.openapi.PedidoFiltroParameter;
import br.com.felipe.gorisfood.domain.filter.PedidoFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pedidos")
@SecurityRequirement(name = "security_auth")public interface PedidoControllerOpenApi {
	
	@Operation(summary = "Pesquisa os pedidos",
			description = "Lista os pedidos de forma paginada e utilizando filtros de pesquisa",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse( responseCode = "400", description = "Parâmetro de pesquisa inválido" , 
			  		  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
	  				  schema = @Schema(implementation = Problema.class)))
			})
	@PedidoFiltroParameter
	@PageableParameter
	PagedModel<PedidoResumidoResponseDTO> pesquisar(
			@Parameter(hidden = true) PedidoFilter pedidoFilter,
			@Parameter(hidden = true) Pageable pageable);
	
	@Operation(summary = "Pesquisa os pedidos por código",
			description = "Pesquisa os pedidos por código do pedido",
			responses = {
					@ApiResponse( responseCode = "400", description = "Parâmetro de pesquisa inválido" , 
							  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
								  				 schema = @Schema(implementation = Problema.class))
					),
					@ApiResponse( responseCode = "404", description = "Pedido não encontrado" , 
					  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
						  				 schema = @Schema(implementation = Problema.class))
					)
			})
	PedidoResponseDTO buscar(@Parameter(description = "Código do pedido", 
		example = "f9981ca4-5a5e-4da3-af04-933861df3e55") String codigoPedido);
	
	@Operation(summary = "Registra um pedido",
			description = "Registra um pedido do cliente",
			responses = {
					@ApiResponse(responseCode = "200", description = "Pedido registrado"),
					@ApiResponse(responseCode = "422", 
						description = "ID de uma das propriedades (restaurante, formaPagamento, cidade ou produto) não cadastrado", 
						content = {@Content(schema = @Schema(implementation = Problema.class), 
					 					 mediaType = MediaType.APPLICATION_JSON_VALUE)})
					})
	PedidoResponseDTO emitir(@RequestBody PedidoRequestDTO pedidoDTO);
}
