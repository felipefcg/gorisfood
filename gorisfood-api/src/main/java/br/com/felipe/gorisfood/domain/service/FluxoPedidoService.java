package br.com.felipe.gorisfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.domain.repository.PedidoRepository;

@Service
public class FluxoPedidoService {
	
	@Autowired
	private EmissaoPedidoService emissaoPedidoService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Transactional
	public void confirmar (String codigoPedido) {
		
		var pedido = emissaoPedidoService.buscar(codigoPedido);
		pedido.confirmar();
		pedidoRepository.save(pedido);
		
		
	}
	
	@Transactional
	public void entregar (String codigoPedido) {
		
		var pedido = emissaoPedidoService.buscar(codigoPedido);
		pedido.entregar();
	}
	
	@Transactional
	public void cancelar (String codigoPedido) {
		
		var pedido = emissaoPedidoService.buscar(codigoPedido);
		pedido.cancelar();
	}
	
}
