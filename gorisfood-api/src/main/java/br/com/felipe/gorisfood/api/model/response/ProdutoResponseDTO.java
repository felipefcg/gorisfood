package br.com.felipe.gorisfood.api.model.response;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoResponseDTO {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Porco com molho agridoce")
	private String nome;
	
	@ApiModelProperty(example = "Deliciosa carne suína ao molho especial")
	private String descricao;
	
	@ApiModelProperty(example = "109.90")
	private BigDecimal preco;
	
	@ApiModelProperty(example = "true")
	private Boolean ativo;
	
}
