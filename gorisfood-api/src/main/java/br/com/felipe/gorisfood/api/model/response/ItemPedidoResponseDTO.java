package br.com.felipe.gorisfood.api.model.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoResponseDTO {
	
	private Long produtoId;
	private String produtoNome;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;
	private String observacao;
}
