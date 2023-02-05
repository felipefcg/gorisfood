package br.com.felipe.gorisfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<PedidoResumidoResponseDTO> listar() {
		return pedidoResumidoAssembler.toDtoList(emissaoPedidoService.listar());
	}
	
	@GetMapping("{pedidoId}")
	public PedidoResponseDTO buscar(@PathVariable Long pedidoId) {
		return pedidoAssembler.toDto(emissaoPedidoService.buscar(pedidoId));
	}
	
	@PostMapping
	public PedidoResponseDTO emitir(@RequestBody @Valid PedidoRequestDTO pedidoDTO) {
		var pedido = pedidoDisassembler.toModel(pedidoDTO);
		return pedidoAssembler.toDto(emissaoPedidoService.salvar(pedido));
	}
}
