package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import br.com.felipe.gorisfood.api.v1.model.request.EstadoRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.EstadoResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Estados")
@SecurityRequirement(name = "security_auth")public interface EstadoControllerOpenApi {
	CollectionModel<EstadoResponseDTO> listar();
	EstadoResponseDTO buscar(Long id);
	EstadoResponseDTO criar(EstadoRequestDTO estadoDTO);
	EstadoResponseDTO alterar(Long id, EstadoRequestDTO estadoDTO);
	void remover(Long id);
}
