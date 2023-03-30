package br.com.felipe.gorisfood.api.model.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoRequestDTO {
	
	@Valid
	@NotNull 
	private RestauranteIdRequestDTO restaurante;
	
	@Valid
	@NotNull
	private FormaPagamentoIdRequestDTO formaPagamento;
	
	@Valid
	@NotNull
	private EnderecoRequestDTO enderecoEntrega;
	
	@Valid
	@NotNull
	@Size(min = 1)
	private List<ItemPedidoResquestDTO> itens;
}
