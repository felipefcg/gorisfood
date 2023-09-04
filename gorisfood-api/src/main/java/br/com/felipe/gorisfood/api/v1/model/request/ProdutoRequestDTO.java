package br.com.felipe.gorisfood.api.v1.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoRequestDTO {


	@NotBlank
	private String nome;
	

	@NotBlank
	private String descricao;
	

	@NotNull
	@PositiveOrZero
	private BigDecimal preco;
	

	@NotNull
	private Boolean ativo;
	
}
