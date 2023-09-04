package br.com.felipe.gorisfood.api.v1.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import br.com.felipe.gorisfood.domain.model.enums.StatusPedido;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
//@JsonFilter("pedidoFilter")
public class PedidoResumidoResponseDTO extends RepresentationModel<PedidoResumidoResponseDTO> {
	

	private String codigo;
	

	private BigDecimal subtotal;
	

	private BigDecimal taxaFrete;
	

	private BigDecimal valorTotal;
	

	private StatusPedido status;
	

	private OffsetDateTime dataCriacao;
	
	private RestauranteApenasNomeResponseDTO restaurante;
	
	private UsuarioResponseDTO cliente;
}
