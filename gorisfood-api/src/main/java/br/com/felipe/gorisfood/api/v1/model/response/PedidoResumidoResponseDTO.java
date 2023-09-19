package br.com.felipe.gorisfood.api.v1.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import br.com.felipe.gorisfood.domain.model.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
//@JsonFilter("pedidoFilter")
public class PedidoResumidoResponseDTO extends RepresentationModel<PedidoResumidoResponseDTO> {
	
	@Schema(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
	private String codigo;
	
	@Schema(example = "298.90")
	private BigDecimal subtotal;
	
	@Schema(example = "10.00")
	private BigDecimal taxaFrete;
	
	@Schema(example = "308.90")
	private BigDecimal valorTotal;
	
	@Schema(enumAsRef = true)
	private StatusPedido status;
	
	@Schema(example = "2023-03-29T10:42:37")
	private OffsetDateTime dataCriacao;
	
	@Schema(description = "Nome do restaurante que foi feito o pedido")
	private RestauranteApenasNomeResponseDTO restaurante;
	
	@Schema(example = "Usuário que efetuou o pedido")
	private UsuarioResponseDTO cliente;
}
