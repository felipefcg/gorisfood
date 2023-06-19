package br.com.felipe.gorisfood.api.v1.openapi.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.v1.model.request.PedidoRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.PedidoResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.PedidoResumidoResponseDTO;
import br.com.felipe.gorisfood.domain.filter.PedidoFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

	@ApiOperation("Pesquisa os pedidos")
	@ApiImplicitParams ({
		@ApiImplicitParam(
				name = "campos", value = "Nome das propriedades para filtrar ana resposta, separado por vírgula",
				paramType = "query", dataTypeClass = String.class, example = "conteudo.cliente.email,conteudo.status"
		)
	})
	@ApiResponses({
		@ApiResponse( responseCode = "400", description = "Parâmetro de pesquisa inválido" , 
			  		  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
	  				  schema = @Schema(implementation = Problema.class)))
	})
	PagedModel<PedidoResumidoResponseDTO> pesquisar(PedidoFilter pedidoFilter, Pageable pageable);
	
	
	@ApiOperation("Pesquisa os pedidos por código")
	@ApiImplicitParam(
			name = "campos", value = "Nome das propriedades para filtrar ana resposta, separado por vírgula",
			paramType = "query", dataTypeClass = String.class, example = "codigo,valorTotal,cliente[id,nome]"
	)
	@ApiResponses ({
		@ApiResponse( responseCode = "400", description = "Parâmetro de pesquisa inválido" , 
					  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
						  				 schema = @Schema(implementation = Problema.class))
		),
		@ApiResponse( responseCode = "404", description = "Pedido não encontrado" , 
		  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			  				 schema = @Schema(implementation = Problema.class))
		)
	})	
	PedidoResponseDTO buscar(@PathVariable String codigoPedido);
	
	@ApiOperation("Registra um pedido")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Pedido registrado"),
		@ApiResponse(responseCode = "422", description = "ID de uma das propriedades (restaurante, formaPagamento, cidade ou produto) não cadastrado", 
					 content = {@Content(schema = @Schema(implementation = Problema.class), 
					 					 mediaType = MediaType.APPLICATION_JSON_VALUE)}
		)
	})
	PedidoResponseDTO emitir(@RequestBody @Valid PedidoRequestDTO pedidoDTO);
}
