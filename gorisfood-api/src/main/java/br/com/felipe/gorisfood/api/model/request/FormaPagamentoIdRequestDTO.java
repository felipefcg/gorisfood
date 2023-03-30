package br.com.felipe.gorisfood.api.model.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoIdRequestDTO {
	
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long id;
}
