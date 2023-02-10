package br.com.felipe.gorisfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import br.com.felipe.gorisfood.api.assembler.PedidoRequestDtoDisassembler;
import br.com.felipe.gorisfood.api.assembler.PedidoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.assembler.PedidoResumidoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.model.request.PedidoRequestDTO;
import br.com.felipe.gorisfood.api.model.response.PedidoResponseDTO;
import br.com.felipe.gorisfood.api.model.response.PedidoResumidoResponseDTO;
import br.com.felipe.gorisfood.domain.model.Usuario;
import br.com.felipe.gorisfood.domain.repository.filter.PedidoFilter;
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
	public List<PedidoResumidoResponseDTO> pesquisar(PedidoFilter pedidoFilter) {
		return pedidoResumidoAssembler.toDtoList(emissaoPedidoService.listar(pedidoFilter));
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
}
