package br.com.felipe.gorisfood.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusPedido {
	CRIADO ("Criado"),
	CONFIRMADO ("Confirmado"),
	ENTREGUE ("Entregue"),
	CANCELADO ("Cancelado");
	
	private String descricao;
}
