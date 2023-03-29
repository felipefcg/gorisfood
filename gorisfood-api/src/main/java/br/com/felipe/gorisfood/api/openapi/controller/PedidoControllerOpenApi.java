package br.com.felipe.gorisfood.api.openapi.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.felipe.gorisfood.api.model.request.PedidoRequestDTO;
import br.com.felipe.gorisfood.api.model.response.PedidoResponseDTO;
import br.com.felipe.gorisfood.api.model.response.PedidoResumidoResponseDTO;
import br.com.felipe.gorisfood.domain.filter.PedidoFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import springfox.documentation.schema.ScalarType;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

	@ApiImplicitParams ({
		@ApiImplicitParam(
				name = "campos", value = "Nome das propriedades para filtrar ana resposta, separado por vírgula",
				paramType = "query", dataTypeClass = String.class, example = "conteudo.cliente.email,conteudo.status"
		)
	})
	public Page<PedidoResumidoResponseDTO> pesquisar(PedidoFilter pedidoFilter, Pageable pageable);
	
	@ApiImplicitParams ({
		@ApiImplicitParam(
				name = "campos", value = "Nome das propriedades para filtrar ana resposta, separado por vírgula",
				paramType = "query", dataTypeClass = String.class, example = "conteudo.cliente.email,conteudo.status"
		)
	})
	public PedidoResponseDTO buscar(@PathVariable String codigoPedido);
	
	public PedidoResponseDTO emitir(@RequestBody @Valid PedidoRequestDTO pedidoDTO);
}
