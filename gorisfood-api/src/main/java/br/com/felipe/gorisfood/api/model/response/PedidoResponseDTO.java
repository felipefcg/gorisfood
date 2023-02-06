package br.com.felipe.gorisfood.api.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import br.com.felipe.gorisfood.domain.model.enums.StatusPedido;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResponseDTO {
	
	private Long id;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private StatusPedido status;
	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataEntrega;
	private RestauranteResumidoResponseDTO restaurante;
	private UsuarioResponseDTO cliente;
	private FormaPagamentoResponseDTO formaPagamento;
	private EnderecoResponseDTO enderecoEntrega;
	private List<ItemPedidoResponseDTO> itens;
}
