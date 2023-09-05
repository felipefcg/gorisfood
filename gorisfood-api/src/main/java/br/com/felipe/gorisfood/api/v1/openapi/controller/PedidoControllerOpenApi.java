package br.com.felipe.gorisfood.api.v1.openapi.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.felipe.gorisfood.api.v1.model.request.PedidoRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.PedidoResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.PedidoResumidoResponseDTO;
import br.com.felipe.gorisfood.domain.filter.PedidoFilter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pedidos")
@SecurityRequirement(name = "security_auth")public interface PedidoControllerOpenApi {
	PagedModel<PedidoResumidoResponseDTO> pesquisar(PedidoFilter pedidoFilter, Pageable pageable);
	PedidoResponseDTO buscar(@PathVariable String codigoPedido);
	PedidoResponseDTO emitir(@RequestBody @Valid PedidoRequestDTO pedidoDTO);
}
