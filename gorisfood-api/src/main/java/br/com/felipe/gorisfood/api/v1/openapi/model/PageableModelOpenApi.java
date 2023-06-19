package br.com.felipe.gorisfood.api.v1.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Pageable")
@Getter
@Setter
public class PageableModelOpenApi {

	@ApiModelProperty(example = "0", value = "Número da página (iniciando em 0)")
	private int pagina;
	
	@ApiModelProperty(example = "10", value = "Quantidade de elementos da página")
	private int tamanhoPagina;
	
	@ApiModelProperty(example = "nome,asc", value = "Nome da propriedade para ordenação")
	private List<String> sort;
}
