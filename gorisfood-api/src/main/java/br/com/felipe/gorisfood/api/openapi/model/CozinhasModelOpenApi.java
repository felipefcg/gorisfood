package br.com.felipe.gorisfood.api.openapi.model;

import br.com.felipe.gorisfood.api.model.response.CozinhaResponseDTO;
import io.swagger.annotations.ApiModel;

//@ApiModel("CozinhasModel")
public class CozinhasModelOpenApi //extends PagedModelOpenApi<CozinhaResponseDTO>
/* TODO: "Removido" essa classe em detrimento da configuração efetuada na classe SpringFoxConfig
*  em que se aproveita apenas da classe PagedModelOpenApi, herdada anteriormente por essa classe
*  para cria a documentação, assim não precisamos ficar criando classes vazias extendendo a classe
*  pai apenas para ficar adicionando na documentação OpenAPI
*  
*  O código que generaliza essa criação está em SpringFoxConfig.buildPageTypeRole(Class<T> classResponseDTO)  
*/
{}
