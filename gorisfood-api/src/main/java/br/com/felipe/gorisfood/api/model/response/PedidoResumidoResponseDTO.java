package br.com.felipe.gorisfood.api.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFilter;

import br.com.felipe.gorisfood.domain.model.enums.StatusPedido;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonFilter("pedidoFilter")
public class PedidoResumidoResponseDTO {
	private String codigo;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private StatusPedido status;
	private OffsetDateTime dataCriacao;
	private RestauranteResumidoResponseDTO restaurante;
	private UsuarioResponseDTO cliente;
}
