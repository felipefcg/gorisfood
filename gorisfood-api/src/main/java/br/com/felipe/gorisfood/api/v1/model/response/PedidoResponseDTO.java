package br.com.felipe.gorisfood.api.v1.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import br.com.felipe.gorisfood.domain.model.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoResponseDTO extends RepresentationModel<PedidoResponseDTO> {
	
	@Schema(example = "1e387510-6710-434b-b761-609e8ca29d31")
	private String codigo;
	
	@Schema(example = "174.40")
	private BigDecimal subtotal;
	
	@Schema(example = "9.50")
	private BigDecimal taxaFrete;
	
	@Schema(example = "183.9")
	private BigDecimal valorTotal;
	
	@Schema(example = "CANCELADO", enumAsRef = true)
	private StatusPedido status;
	
	@Schema(example = "2023-03-29T10:42:37Z")
	private OffsetDateTime dataCriacao;
	
	@Schema(example = "2023-03-29T10:42:37Z")
	private OffsetDateTime dataConfirmacao;
	
	@Schema(example = "2023-03-29T10:42:37Z")
	private OffsetDateTime dataCancelamento;
	
	@Schema(example = "2023-03-29T10:42:37Z")
	private OffsetDateTime dataEntrega;
	
	@Schema(description = "Dados do restaurante que está sendo feito o pedido")
	private RestauranteApenasNomeResponseDTO restaurante;
	
	@Schema(description = "Dados do usuário que efetuou o pedido")
	private UsuarioResponseDTO cliente;
	
	@Schema(description = "Dados da forma de pagamento que está sendo utilizado no pedido")
	private FormaPagamentoResponseDTO formaPagamento;
	
	@Schema(description = "Dados do endereço de entrega do pedido")
	private EnderecoResponseDTO enderecoEntrega;
	
	@Schema(description = "Itens do pedido")
	private List<ItemPedidoResponseDTO> itens;
}
