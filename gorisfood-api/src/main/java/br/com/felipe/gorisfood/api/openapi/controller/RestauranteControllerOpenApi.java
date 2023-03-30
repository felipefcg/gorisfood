package br.com.felipe.gorisfood.api.openapi.controller;

import java.util.List;

import br.com.felipe.gorisfood.api.model.response.RestauranteResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Api(tags = "Restaurantes")
@Tag(name= "Restaurantes", description = "Endpoints de acesso às informações relacionadas ao restaurante")
public interface RestauranteControllerOpenApi {

	@ApiOperation(value = "Listar restaurantes")
	@ApiImplicitParam(value = "Nome da projeção de restaurantes", 
						name = "projecao", paramType = "query", dataTypeClass = String.class, allowableValues = "apenas-nome")
	public List<RestauranteResponseDTO> listar();
	
	@ApiOperation(value = "Listar restaurantes", hidden = true)
	public List<RestauranteResponseDTO> listarApenasNome();
}
