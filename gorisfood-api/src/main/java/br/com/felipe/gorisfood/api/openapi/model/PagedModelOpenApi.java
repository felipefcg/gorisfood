package br.com.felipe.gorisfood.api.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Page")
@Getter
@Setter
public class PagedModelOpenApi<T> {

	private List<T> conteudo;

	@ApiModelProperty(example = "10", value = "Quantidade de registros da página")
	private int tamanhoPagina;
	
	@ApiModelProperty(example = "45", value = "Total de registros")
	private int totalElementos;
	
	@ApiModelProperty(example = "5", value = "Total de páginas")
	private int totalPaginas;
	
	@ApiModelProperty(example = "0", value = "Número da páginas (começa em 0")
	private int pagina;
}
