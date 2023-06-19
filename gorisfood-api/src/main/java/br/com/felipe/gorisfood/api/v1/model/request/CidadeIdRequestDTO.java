package br.com.felipe.gorisfood.api.v1.model.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeIdRequestDTO {
	
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long id;
}
