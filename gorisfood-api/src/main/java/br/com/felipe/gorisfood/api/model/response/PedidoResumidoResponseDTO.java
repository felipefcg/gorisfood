package br.com.felipe.gorisfood.api.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import br.com.felipe.gorisfood.domain.model.enums.StatusPedido;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoResumidoResponseDTO {
	private Long id;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private StatusPedido status;
	private OffsetDateTime dataCriacao;
	private RestauranteResumidoResponseDTO restaurante;
	private UsuarioResponseDTO cliente;
}
