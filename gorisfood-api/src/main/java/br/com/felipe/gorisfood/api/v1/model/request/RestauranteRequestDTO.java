package br.com.felipe.gorisfood.api.v1.model.request;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteRequestDTO {
	

	@NotBlank
	private String nome;
	

	@NotNull
	@PositiveOrZero
	private BigDecimal taxaFrete;
	
	@Valid
	@NotNull
	private CozinhaIdRequestDTO cozinha;
	
	@Valid
	@NotNull
	private EnderecoRequestDTO endereco;
	
}
