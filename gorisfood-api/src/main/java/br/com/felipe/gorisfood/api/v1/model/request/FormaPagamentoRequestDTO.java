package br.com.felipe.gorisfood.api.v1.model.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoRequestDTO {
	
	@ApiModelProperty(example = "Dinheiro", required = true)
	@NotBlank
	private String descricao;
}
