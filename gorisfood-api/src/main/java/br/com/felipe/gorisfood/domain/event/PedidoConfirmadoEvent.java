package br.com.felipe.gorisfood.domain.event;

import br.com.felipe.gorisfood.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoConfirmadoEvent {
	private Pedido pedido;
}
