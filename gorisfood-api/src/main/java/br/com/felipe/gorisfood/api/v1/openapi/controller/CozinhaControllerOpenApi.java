package br.com.felipe.gorisfood.api.v1.openapi.controller;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import br.com.felipe.gorisfood.api.v1.model.request.CozinhaRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.CozinhaResponseDTO;
import br.com.felipe.gorisfood.domain.model.Cozinha;

public interface CozinhaControllerOpenApi {

	PagedModel<CozinhaResponseDTO> listar (Pageable pagable);
	
	CozinhaResponseDTO buscar (Long id);
	
	CozinhaResponseDTO criar(CozinhaRequestDTO cozinhaDTO);
	
	CozinhaResponseDTO alterar(Long id, CozinhaRequestDTO cozinhaDTO);
	
	void remover(Long id);
	
	Optional<Cozinha> buscarPrimeiro();
}
