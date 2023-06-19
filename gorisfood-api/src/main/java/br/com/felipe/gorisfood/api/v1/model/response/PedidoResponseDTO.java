package br.com.felipe.gorisfood.api.v1.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import br.com.felipe.gorisfood.domain.model.enums.StatusPedido;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoResponseDTO extends RepresentationModel<PedidoResponseDTO> {
	
	@ApiModelProperty(example = "1e387510-6710-434b-b761-609e8ca29d31")
	private String codigo;
	
	@ApiModelProperty(example = "174.40")
	private BigDecimal subtotal;
	
	@ApiModelProperty(example = "9.50")
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(example = "183.9")
	private BigDecimal valorTotal;
	
	@ApiModelProperty(example = "CANCELADO")
	private StatusPedido status;
	
	@ApiModelProperty(example = "2023-03-29T10:42:37Z")
	private OffsetDateTime dataCriacao;
	
	@ApiModelProperty(example = "2023-03-29T10:42:37Z")
	private OffsetDateTime dataConfirmacao;
	
	@ApiModelProperty(example = "2023-03-29T10:42:37Z")
	private OffsetDateTime dataCancelamento;
	
	@ApiModelProperty(example = "2023-03-29T10:42:37Z")
	private OffsetDateTime dataEntrega;
	
	private RestauranteApenasNomeResponseDTO restaurante;
	private UsuarioResponseDTO cliente;
	
	private FormaPagamentoResponseDTO formaPagamento;
	private EnderecoResponseDTO enderecoEntrega;
	private List<ItemPedidoResponseDTO> itens;
}
