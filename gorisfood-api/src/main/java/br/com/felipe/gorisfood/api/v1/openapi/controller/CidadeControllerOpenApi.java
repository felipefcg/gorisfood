package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import br.com.felipe.gorisfood.api.v1.model.request.CidadeRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.CidadeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {
	
	@Operation(summary = "Lista as cidades")
	CollectionModel<CidadeResponseDTO> listar();

	@Operation(summary = "Busca uma cidade por Od")
	CidadeResponseDTO buscar(Long id);
	
	@Operation(summary = "Cadastra uma cidade", description = "Cadastro de uma cidade, " +
			"necessita de um estado e um nome válido")
	CidadeResponseDTO criar(CidadeRequestDTO cidadeDTO);
	
	@Operation(summary = "Atualiza uma cidade")
	CidadeResponseDTO alterar(Long id, CidadeRequestDTO cidadeDTO);
	
	@Operation(summary = "Exclui uma cidade")
	void remover(Long id);
}
