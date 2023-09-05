package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import br.com.felipe.gorisfood.api.v1.model.request.CidadeRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.CidadeResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {
	
	CollectionModel<CidadeResponseDTO> listar();

	CidadeResponseDTO buscar(Long id);
	
	CidadeResponseDTO criar(CidadeRequestDTO cidadeDTO);
	
	CidadeResponseDTO alterar(Long id, CidadeRequestDTO cidadeDTO);
	
	void remover(Long id);
}
