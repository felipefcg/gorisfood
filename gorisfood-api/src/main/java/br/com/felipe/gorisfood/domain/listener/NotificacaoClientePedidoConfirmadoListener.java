package br.com.felipe.gorisfood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import br.com.felipe.gorisfood.domain.event.PedidoConfirmadoEvent;
import br.com.felipe.gorisfood.domain.service.EnvioEmailService;
import br.com.felipe.gorisfood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoConfirmadoListener {
	
	@Autowired
	private EnvioEmailService emailService; 
	
//	@EventListener
	@TransactionalEventListener
	public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
		var pedido = event.getPedido();
		var assunto = String.format("%s - Pedido Confirmado", pedido.getRestaurante().getNome());
		var mensagem = Mensagem.builder()
						.assunto(assunto)
						.template("pedido-confirmado.html")
						.variavel("pedido", pedido)
						.destinatario(pedido.getCliente().getEmail())
						.build();
		
		emailService.enviar(mensagem);		
	}
}
