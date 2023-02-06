package br.com.felipe.gorisfood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.domain.service.FluxoPedidoService;

@RequestMapping(value = "pedidos/{pedidoId}", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class FluxoPedidoController {

	@Autowired
	private FluxoPedidoService fluxoPedidoService;
	
	
	@PutMapping("confirmado")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmar(@PathVariable Long pedidoId) {
		fluxoPedidoService.confirmar(pedidoId);
	}
	
	@PutMapping("entregue")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entregar(@PathVariable Long pedidoId) {
		fluxoPedidoService.entregar(pedidoId);
	}
	
	@PutMapping("cancelado")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelar(@PathVariable Long pedidoId) {
		fluxoPedidoService.cancelar(pedidoId);
	}
}
