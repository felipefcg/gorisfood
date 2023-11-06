package br.com.felipe.gorisfood.api.v1.model.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoRequestDTO {
	
	@Valid
	@NotNull
	@Schema(description = "Dados do restaurante que está sendo feito o pedido", 
	requiredMode = RequiredMode.REQUIRED)
	private RestauranteIdRequestDTO restaurante;
	
	@Valid
	@NotNull
	@Schema(description = "Dados da forma de pagamento que está sendo utilizado no pedido", 
			requiredMode = RequiredMode.REQUIRED)
	private FormaPagamentoIdRequestDTO formaPagamento;
	
	@Valid
	@NotNull
	@Schema(description = "Dados do endereço de entrega do pedido", 
			requiredMode = RequiredMode.REQUIRED)
	private EnderecoRequestDTO enderecoEntrega;
	
	@Valid
	@NotNull
	@Size(min = 1)
	@Schema(description = "Itens do pedido", 
			requiredMode = RequiredMode.REQUIRED)
	private List<ItemPedidoResquestDTO> itens;
}
