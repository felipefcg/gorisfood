package br.com.felipe.gorisfood.api.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import br.com.felipe.gorisfood.domain.model.enums.StatusPedido;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
//@JsonFilter("pedidoFilter")
public class PedidoResumidoResponseDTO extends RepresentationModel<PedidoResumidoResponseDTO> {
	
	@ApiModelProperty(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
	private String codigo;
	
	@ApiModelProperty(example = "298.90")
	private BigDecimal subtotal;
	
	@ApiModelProperty(example = "10.00")
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(example = "308.90")
	private BigDecimal valorTotal;
	
	@ApiModelProperty(example = "CRIADO")
	private StatusPedido status;
	
	@ApiModelProperty(example = "2023-03-29T10:42:37Z")
	private OffsetDateTime dataCriacao;
	
	private RestauranteResumidoResponseDTO restaurante;
	
	private UsuarioResponseDTO cliente;
}
