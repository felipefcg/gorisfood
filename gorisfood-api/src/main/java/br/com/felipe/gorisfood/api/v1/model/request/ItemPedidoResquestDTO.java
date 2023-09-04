package br.com.felipe.gorisfood.api.v1.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoResquestDTO {


	@NotNull
	private Long produtoId;
	

	@NotNull
	@Positive
	private Integer quantidade;
	

	private String observacao;
}
