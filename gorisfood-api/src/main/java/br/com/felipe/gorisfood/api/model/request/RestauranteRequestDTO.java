package br.com.felipe.gorisfood.api.model.request;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import br.com.felipe.gorisfood.core.validation.Grupo;
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
//	@ConvertGroup(from = Default.class, to = Grupo.CozinhaId.class)
	@NotNull
	private CozinhaIdRequestDTO cozinha;
	
}
