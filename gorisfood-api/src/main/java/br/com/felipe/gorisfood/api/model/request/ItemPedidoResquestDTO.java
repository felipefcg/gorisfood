package br.com.felipe.gorisfood.api.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoResquestDTO {

	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long produtoId;
	
	@ApiModelProperty(example = "2", required = true)
	@NotNull
	@Positive
	private Integer quantidade;
	
	@ApiModelProperty(example = "Sem cebola")
	private String observacao;
}
