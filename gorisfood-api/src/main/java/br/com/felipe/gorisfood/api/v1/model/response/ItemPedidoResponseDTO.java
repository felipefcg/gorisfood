package br.com.felipe.gorisfood.api.v1.model.response;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoResponseDTO extends RepresentationModel<ItemPedidoResponseDTO> {
	
	@Schema(example = "")
	private Long produtoId;
	
	@Schema(example = "")
	private String produtoNome;
	
	@Schema(example = "")
	private Integer quantidade;
	
	@Schema(example = "")
	private BigDecimal precoUnitario;
	
	@Schema(example = "")
	private BigDecimal precoTotal;
	
	@Schema(example = "")
	private String observacao;
}
