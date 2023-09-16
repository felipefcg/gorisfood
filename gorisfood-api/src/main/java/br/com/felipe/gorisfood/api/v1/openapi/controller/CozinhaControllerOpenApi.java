package br.com.felipe.gorisfood.api.v1.openapi.controller;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import br.com.felipe.gorisfood.api.v1.model.request.CozinhaRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.CozinhaResponseDTO;
import br.com.felipe.gorisfood.core.openapi.PageableParameter;
import br.com.felipe.gorisfood.domain.model.Cozinha;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cozinhas")
@SecurityRequirement(name = "security_auth")public interface CozinhaControllerOpenApi {

	@PageableParameter
	PagedModel<CozinhaResponseDTO> listar (@Parameter(hidden = true) Pageable pagable);
	
	CozinhaResponseDTO buscar (Long id);
	
	CozinhaResponseDTO criar(CozinhaRequestDTO cozinhaDTO);
	
	CozinhaResponseDTO alterar(Long id, CozinhaRequestDTO cozinhaDTO);
	
	void remover(Long id);
	
	Optional<Cozinha> buscarPrimeiro();
}
