package br.com.felipe.gorisfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.domain.service.EnvioEmailService.Mensagem;

@Service
public class FluxoPedidoService {
	
	@Autowired
	private EmissaoPedidoService emissaoPedidoService;
	
	@Autowired
	private EnvioEmailService emailService; 
	
	@Transactional
	public void confirmar (String codigoPedido) {
		
		var pedido = emissaoPedidoService.buscar(codigoPedido);
		pedido.confirmar();
		
		var assunto = String.format("%s - Pedido Confirmado", pedido.getRestaurante().getNome());
		
		var mensagem = Mensagem.builder()
			.assunto(assunto)
			.template("pedido-confirmado.html")
			.variavel("pedido", pedido)
			.destinatario(pedido.getCliente().getEmail())
			.build();
		
		emailService.enviar(mensagem);
		
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
