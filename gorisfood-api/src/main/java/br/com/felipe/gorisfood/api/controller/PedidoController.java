package br.com.felipe.gorisfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.assembler.PedidoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.assembler.PedidoResumidoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.model.response.PedidoResponseDTO;
import br.com.felipe.gorisfood.api.model.response.PedidoResumidoResponseDTO;
import br.com.felipe.gorisfood.domain.service.EmissaoPedidoService;

@RestController
@RequestMapping(value = "pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController {

	@Autowired
	private EmissaoPedidoService pedidoService;
	
	@Autowired
	private PedidoResponseDtoAssembler pedidoAssembler;
	
	@Autowired
	private PedidoResumidoResponseDtoAssembler pedidoResumidoAssembler;
	
	@GetMapping
	public List<PedidoResumidoResponseDTO> listar() {
		return pedidoResumidoAssembler.toDtoList(pedidoService.listar());
	}
	
	@GetMapping("{pedidoId}")
	public PedidoResponseDTO buscar(@PathVariable Long pedidoId) {
		return pedidoAssembler.toDto(pedidoService.buscar(pedidoId));
	}
}
