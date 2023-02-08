package br.com.felipe.gorisfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoPedidoService {
	
	@Autowired
	private EmissaoPedidoService emissaoPedidoService;
	
	@Transactional
	public void confirmar (Long pedidoId) {
		
		var pedido = emissaoPedidoService.buscar(pedidoId);
		pedido.confirmar();
		
	}
	
	@Transactional
	public void entregar (Long pedidoId) {
		
		var pedido = emissaoPedidoService.buscar(pedidoId);
		pedido.entregar();
		
	}
	
	@Transactional
	public void cancelar (Long pedidoId) {
		
		var pedido = emissaoPedidoService.buscar(pedidoId);
		pedido.cancelar();
	}
	
}
