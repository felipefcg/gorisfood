package br.com.felipe.gorisfood.api.v1.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import br.com.felipe.gorisfood.domain.model.enums.StatusPedido;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoResponseDTO extends RepresentationModel<PedidoResponseDTO> {
	

	private String codigo;
	

	private BigDecimal subtotal;
	

	private BigDecimal taxaFrete;
	

	private BigDecimal valorTotal;
	

	private StatusPedido status;
	

	private OffsetDateTime dataCriacao;
	

	private OffsetDateTime dataConfirmacao;
	

	private OffsetDateTime dataCancelamento;
	

	private OffsetDateTime dataEntrega;
	
	private RestauranteApenasNomeResponseDTO restaurante;
	private UsuarioResponseDTO cliente;
	
	private FormaPagamentoResponseDTO formaPagamento;
	private EnderecoResponseDTO enderecoEntrega;
	private List<ItemPedidoResponseDTO> itens;
}
