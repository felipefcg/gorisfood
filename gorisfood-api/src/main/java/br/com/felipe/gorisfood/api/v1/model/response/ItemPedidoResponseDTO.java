package br.com.felipe.gorisfood.api.v1.model.response;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoResponseDTO extends RepresentationModel<ItemPedidoResponseDTO> {
	
	@ApiModelProperty(example = "1")
	private Long produtoId;
	
	@ApiModelProperty(example = "Salada picante com carne grelhada")
	private String produtoNome;
	
	@ApiModelProperty(example = "2")
	private Integer quantidade;
	
	@ApiModelProperty(example = "87.20")
	private BigDecimal precoUnitario;
	
	@ApiModelProperty(example = "174.40")
	private BigDecimal precoTotal;
	
	@ApiModelProperty(example = "Sem cebola")
	private String observacao;
}
