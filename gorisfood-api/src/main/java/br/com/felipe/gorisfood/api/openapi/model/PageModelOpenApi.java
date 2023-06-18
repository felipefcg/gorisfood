package br.com.felipe.gorisfood.api.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("PageModel")
@Getter
@Setter
public class PageModelOpenApi {

	@ApiModelProperty(example = "10", value = "Quantidade de registros da página")
	private int size;
	
	@ApiModelProperty(example = "45", value = "Total de registros")
	private int totalElements;
	
	@ApiModelProperty(example = "5", value = "Total de páginas")
	private int totalPages;
	
	@ApiModelProperty(example = "0", value = "Número da páginas (começa em 0")
	private int number;
}
