package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.v1.model.request.SenhaRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.request.UsuarioComSenhaRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.request.UsuarioRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.UsuarioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuários")
@SecurityRequirement(name = "security_auth")public interface UsuarioControllerOpenApi {
	
	@Operation(summary = "Lista os usuários")
	public CollectionModel<UsuarioResponseDTO> listar();
	
	@Operation(summary = "Busca um usuário por ID",
			responses = {
					@ApiResponse(responseCode = "400", description = "ID do usuário inválido",
							 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
									 			schema = @Schema(implementation = Problema.class))
					),
					@ApiResponse(responseCode = "404", description = "Usuário não encontrado",
				 			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							 			schema = @Schema(implementation = Problema.class))
					)
			})
	UsuarioResponseDTO buscar(@Parameter(description = "ID do usuário", example = "1", required = true) Long id);
	
	@Operation(summary = "Cadastra um usuário",
			responses = {
					@ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso")
			})
	UsuarioResponseDTO criar(@RequestBody UsuarioComSenhaRequestDTO dto);
	
	@Operation(summary = "Atualiza um usuário por ID",
			responses = {
					@ApiResponse(responseCode = "204", description = "Usuários atualizado com sucesso"),
					@ApiResponse(responseCode = "404", description = "Usuário não encontrado", 
								content = {@Content(schema = @Schema(implementation = Problema.class), 
										   mediaType = MediaType.APPLICATION_JSON_VALUE)}
					)
			})
	UsuarioResponseDTO alterar(
			@Parameter(description = "ID do usuário", example = "1", required = true) Long id, 
			@RequestBody UsuarioRequestDTO dto);
	
	
	@Operation(summary = "Atualiza a senha de um usuário",
			responses = {
					@ApiResponse(responseCode = "204", description = "Senha atualizado com sucesso"),
					@ApiResponse(responseCode = "404", description = "Usuário não encontrado", 
								content = {@Content(schema = @Schema(implementation = Problema.class), 
										   mediaType = MediaType.APPLICATION_JSON_VALUE)}
					)
			})
	void alterarSenha(
			@Parameter(description = "ID do usuário", example = "1", required = true) Long id, 
			@RequestBody SenhaRequestDTO dto);
}
