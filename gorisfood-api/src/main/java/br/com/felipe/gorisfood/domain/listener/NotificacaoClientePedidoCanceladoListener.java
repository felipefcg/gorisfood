package br.com.felipe.gorisfood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import br.com.felipe.gorisfood.domain.event.PedidoCanceladoEvent;
import br.com.felipe.gorisfood.domain.service.EnvioEmailService;
import br.com.felipe.gorisfood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoCanceladoListener {
	
	@Autowired
	private EnvioEmailService emailService; 
	
	@TransactionalEventListener
	public void aoConfirmarPedido(PedidoCanceladoEvent event) {
		var pedido = event.getPedido();
		var assunto = String.format("%s - Pedido Cancelado", pedido.getRestaurante().getNome());
		var mensagem = Mensagem.builder()
						.assunto(assunto)
						.template("emails/pedido-cancelado.html")
						.variavel("pedido", pedido)
						.destinatario(pedido.getCliente().getEmail())
						.build();
		
		emailService.enviar(mensagem);		
	}
}
