package br.com.felipe.gorisfood.api.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.assembler.PedidoRequestDtoDisassembler;
import br.com.felipe.gorisfood.api.assembler.PedidoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.assembler.PedidoResumidoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.model.request.PedidoRequestDTO;
import br.com.felipe.gorisfood.api.model.response.PedidoResponseDTO;
import br.com.felipe.gorisfood.api.model.response.PedidoResumidoResponseDTO;
import br.com.felipe.gorisfood.core.data.PageableTranslator;
import br.com.felipe.gorisfood.domain.filter.PedidoFilter;
import br.com.felipe.gorisfood.domain.model.Pedido;
import br.com.felipe.gorisfood.domain.model.Usuario;
import br.com.felipe.gorisfood.domain.service.EmissaoPedidoService;

@RestController
@RequestMapping(value = "pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController {

	@Autowired
	private EmissaoPedidoService emissaoPedidoService;

	@Autowired
	private PedidoRequestDtoDisassembler pedidoDisassembler;
	
	@Autowired
	private PedidoResponseDtoAssembler pedidoAssembler;
	
	@Autowired
	private PedidoResumidoResponseDtoAssembler pedidoResumidoAssembler;
	
	@GetMapping
	public Page<PedidoResumidoResponseDTO> pesquisar(PedidoFilter pedidoFilter, Pageable pageable) {
		Page<Pedido> pagePedidos = emissaoPedidoService.listar(pedidoFilter, traduzirPageable(pageable));
		List<PedidoResumidoResponseDTO> pedidosResumidosDTO = pedidoResumidoAssembler.toDtoList(pagePedidos.getContent());
		return new PageImpl<>(pedidosResumidosDTO, pageable, pagePedidos.getTotalElements());
	}
	
//	@GetMapping
//	public MappingJacksonValue listar(@RequestParam(required = false) String[] campos) {
//		List<PedidoResumidoResponseDTO> pedidosDTO = pedidoResumidoAssembler.toDtoList(emissaoPedidoService.listar());
//
//		MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosDTO);
//		SimpleFilterProvider filterProvider = new SimpleFilterProvider();
//		filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
//		
//		if(ArrayUtils.isNotEmpty(campos)) {
//			filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos));
//		}
//		
//		pedidosWrapper.setFilters(filterProvider);
//		return pedidosWrapper;
//	}
	
	@GetMapping("{codigoPedido}")
	public PedidoResponseDTO buscar(@PathVariable String codigoPedido) {
		return pedidoAssembler.toDto(emissaoPedidoService.buscar(codigoPedido));
	}
	
	@PostMapping
	public PedidoResponseDTO emitir(@RequestBody @Valid PedidoRequestDTO pedidoDTO) {
		var pedido = pedidoDisassembler.toModel(pedidoDTO);
		pedido.setCliente(new Usuario());
		pedido.getCliente().setId(1L);
		
		pedido = emissaoPedidoService.emitir(pedido);
		return pedidoAssembler.toDto(pedido);
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
//			"nomeCliente", "cliente.nome"
			"cliente.nome", "cliente.nome"
		);
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}
}
