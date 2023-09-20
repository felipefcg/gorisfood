package br.com.felipe.gorisfood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.v1.model.request.RestauranteRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.RestauranteApenasNomeResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.RestauranteBasicoResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.RestauranteResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Restaurantes")
@SecurityRequirement(name = "security_auth")public interface RestauranteControllerOpenApi {

	@Operation(parameters = {
			@Parameter(name = "projecao",
					description = "Nome da projeção",
					example = "apenas-nome",
					in = ParameterIn.QUERY,
					required = false)
	})
	CollectionModel<RestauranteBasicoResponseDTO> listar();
	
	@Operation(hidden = true)
	CollectionModel<RestauranteApenasNomeResponseDTO> listarApenasNome();
	RestauranteResponseDTO buscar(Long id);
	RestauranteResponseDTO criar(RestauranteRequestDTO restauranteDTO);
	RestauranteResponseDTO alterar(Long id, RestauranteRequestDTO restauranteDTO);
	ResponseEntity<Void> ativar(Long id);
	ResponseEntity<Void> inativar(Long id);
	ResponseEntity<Void> ativarMultiplos(List<Long> id);
	ResponseEntity<Void> inativarMultiplos(List<Long> id);
	ResponseEntity<Void> abrir(Long id);
	ResponseEntity<Void> fechar(Long id);
}
