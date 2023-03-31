package br.com.felipe.gorisfood.api.model.request;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteRequestDTO {
	
	@ApiModelProperty(example = "Thai Gourmet", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "9.99", required = true)
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
