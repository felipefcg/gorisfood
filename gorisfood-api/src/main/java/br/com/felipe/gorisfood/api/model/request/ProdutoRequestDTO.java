package br.com.felipe.gorisfood.api.model.request;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
	
	@PositiveOrZero
	private BigDecimal preco;
	
	@NotNull
	private Boolean ativo;
	
	@Valid
	@NotNull
	private RestauranteIdRequestDTO restaurante;
}
