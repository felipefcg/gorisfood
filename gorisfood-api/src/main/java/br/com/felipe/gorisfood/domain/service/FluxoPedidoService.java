package br.com.felipe.gorisfood.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.domain.exception.EntidadeInconsistenteException;
import br.com.felipe.gorisfood.domain.model.Pedido;
import br.com.felipe.gorisfood.domain.model.enums.StatusPedido;

@Service
public class FluxoPedidoService {
	
	@Autowired
	private EmissaoPedidoService emissaoPedidoService;
	
	@Transactional
	public void confirmar (Long pedidoId) {
		
		var pedido = this.alterarStatus(pedidoId, StatusPedido.CRIADO, StatusPedido.CONFIRMADO);
		pedido.setDataConfirmacao(OffsetDateTime.now());
		
	}
	
	@Transactional
	public void entregar (Long pedidoId) {
		
		var pedido = this.alterarStatus(pedidoId, StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE);
		pedido.setDataEntrega(OffsetDateTime.now());
		
	}
	
	@Transactional
	public void cancelar (Long pedidoId) {
		
		var pedido = this.alterarStatus(pedidoId, StatusPedido.CRIADO, StatusPedido.CANCELADO);
		pedido.setDataCancelamento(OffsetDateTime.now());
	}
	
	private Pedido alterarStatus(Long pedidoId, StatusPedido statusAtual, StatusPedido statusNovo) {
		
		var pedido = emissaoPedidoService.buscar(pedidoId);
		if(!statusAtual.equals(pedido.getStatus())) {
			throw new EntidadeInconsistenteException(String.format("Status do pedido %d não pode ser alterado de %s para %s", 
					pedido.getId(), pedido.getStatus().getDescricao(), statusNovo.getDescricao()));
		}
		pedido.setStatus(statusNovo);
		return pedido;
	}
}
