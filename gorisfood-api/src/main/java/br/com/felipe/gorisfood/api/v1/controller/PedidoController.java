package br.com.felipe.gorisfood.api.v1.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.v1.assembler.PedidoRequestDtoDisassembler;
import br.com.felipe.gorisfood.api.v1.assembler.PedidoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.v1.assembler.PedidoResumidoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.v1.model.request.PedidoRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.PedidoResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.PedidoResumidoResponseDTO;
import br.com.felipe.gorisfood.api.v1.openapi.controller.PedidoControllerOpenApi;
import br.com.felipe.gorisfood.core.data.PageWrapper;
import br.com.felipe.gorisfood.core.data.PageableTranslator;
import br.com.felipe.gorisfood.core.security.AuthUserSecurity;
import br.com.felipe.gorisfood.domain.filter.PedidoFilter;
import br.com.felipe.gorisfood.domain.model.Pedido;
import br.com.felipe.gorisfood.domain.model.Usuario;
import br.com.felipe.gorisfood.domain.service.EmissaoPedidoService;

@RestController
@RequestMapping(value = "v1/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

	@Autowired
	private EmissaoPedidoService emissaoPedidoService;

	@Autowired
	private PedidoRequestDtoDisassembler pedidoDisassembler;
	
	@Autowired
	private PedidoResponseDtoAssembler pedidoAssembler;
	
	@Autowired
	private PedidoResumidoResponseDtoAssembler pedidoResumidoAssembler;

	@Autowired
	private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

	@Autowired
	private AuthUserSecurity authUserSecurity;
	
	private PageWrapper<Pedido> pagePedidos;
	
	@GetMapping
	public PagedModel<PedidoResumidoResponseDTO> pesquisar(PedidoFilter pedidoFilter, Pageable pageable) {
		Page<Pedido> pagePedidos = emissaoPedidoService.listar(pedidoFilter, traduzirPageable(pageable));
		pagePedidos = new PageWrapper<>(pagePedidos, pageable);
		return pagedResourcesAssembler.toModel(pagePedidos, pedidoResumidoAssembler);
	}
	
	@GetMapping("{codigoPedido}")
	public PedidoResponseDTO buscar(@PathVariable String codigoPedido) {
		return pedidoAssembler.toModel(emissaoPedidoService.buscar(codigoPedido));
	}
	
	@PostMapping
	public PedidoResponseDTO emitir(@RequestBody @Valid PedidoRequestDTO pedidoDTO) {
		var pedido = pedidoDisassembler.toModel(pedidoDTO);
		pedido.setCliente(new Usuario());
		pedido.getCliente().setId(authUserSecurity.getUsuarioId());
		
		pedido = emissaoPedidoService.emitir(pedido);
		return pedidoAssembler.toModel(pedido);
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = Map.of(
			"codigo", "codigo", 
			"subtotal", "subtotal",
			"taxaFrete", "taxaFrete",
			"valorTotal", "valorTotal",
			"status", "status",
			"restaurante.id", "restaurante.id",
			"restaurante.nome", "restaurante.nome",
			"cliente.nome", "cliente.nome"
		);
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}
}
