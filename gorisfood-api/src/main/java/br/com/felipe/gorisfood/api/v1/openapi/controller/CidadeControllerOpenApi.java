package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import br.com.felipe.gorisfood.api.v1.model.request.CidadeRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.CidadeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {
	
	@Operation(summary = "Lista as cidades")
	CollectionModel<CidadeResponseDTO> listar();

	@Operation(summary = "Busca uma cidade por Od")
	CidadeResponseDTO buscar(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long id);
	
	@Operation(summary = "Cadastra uma cidade", description = "Cadastro de uma cidade, " +
			"necessita de um estado e um nome válido")
	CidadeResponseDTO criar(@RequestBody(description = "Representação de uma nova cidade", required = true ) CidadeRequestDTO cidadeDTO);
	
	@Operation(summary = "Atualiza uma cidade")
	CidadeResponseDTO alterar(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long id, 
			@RequestBody(description = "Representação de uma nova cidade", required = true ) CidadeRequestDTO cidadeDTO);
	
	@Operation(summary = "Exclui uma cidade")
	void remover(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long id);
}
