package br.com.felipe.gorisfood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Link;

import br.com.felipe.gorisfood.api.model.response.CidadeResponseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CozinhasModel")
@Getter
@Setter
public class CozinhasModelOpenApi //extends PagedModelOpenApi<CozinhaResponseDTO>
/* TODO: "Removido" essa classe em detrimento da configuração efetuada na classe SpringFoxConfig
*  em que se aproveita apenas da classe PagedModelOpenApi, herdada anteriormente por essa classe
*  para cria a documentação, assim não precisamos ficar criando classes vazias extendendo a classe
*  pai apenas para ficar adicionando na documentação OpenAPI
*  
*  O código que generaliza essa criação está em SpringFoxConfig.buildPageTypeRole(Class<T> classResponseDTO)
*  
*  17/06/2023 - retornando a utilizar essa classe por causa do PagedModel do Hateoas. Até essa data essa classe era vazia.
*/
{

	private CozinhasEmbeddedModelOpenApi _embedded;
	private Link _links;
	private PageModelOpenApi page;
	
	@ApiModel("CozinhasEmdddedModel")
	@Data
	public class CozinhasEmbeddedModelOpenApi {	
		private List<CidadeResponseDTO> cozinhas;
	}

}
