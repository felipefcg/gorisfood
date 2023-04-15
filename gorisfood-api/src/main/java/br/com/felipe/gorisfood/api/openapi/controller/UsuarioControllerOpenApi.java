package br.com.felipe.gorisfood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.model.request.SenhaRequestDTO;
import br.com.felipe.gorisfood.api.model.request.UsuarioComSenhaRequestDTO;
import br.com.felipe.gorisfood.api.model.request.UsuarioRequestDTO;
import br.com.felipe.gorisfood.api.model.response.UsuarioResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {

	@ApiOperation("Lista os usuários")
	public CollectionModel<UsuarioResponseDTO> listar();
	
	@ApiOperation("Busca um usuário por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID do usuário inválido",
					 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							 			schema = @Schema(implementation = Problema.class))
		),
		@ApiResponse(responseCode = "404", description = "Usuário não encontrado",
		 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				 			schema = @Schema(implementation = Problema.class))
		)
	})
	UsuarioResponseDTO buscar(@ApiParam(value = "ID do usuário", required = true) Long id);
	
	@ApiOperation("Cadastra um usuário")
	@ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso")
	UsuarioResponseDTO criar(UsuarioComSenhaRequestDTO dto);
	
	@ApiOperation("Atualiza um usuário por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Usuários atualizado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Usuário não encontrado", 
					content = {@Content(schema = @Schema(implementation = Problema.class), 
							   mediaType = MediaType.APPLICATION_JSON_VALUE)}
		)
	})
	UsuarioResponseDTO alterar(@ApiParam(value = "ID do usuário", required = true) Long id, UsuarioRequestDTO dto);
	
	@ApiOperation("Atualiza a senha de um usuário")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Senha atualizado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Usuário não encontrado", 
					content = {@Content(schema = @Schema(implementation = Problema.class), 
							   mediaType = MediaType.APPLICATION_JSON_VALUE)}
		)
	})
	void alterarSenha(@ApiParam(value = "ID do usuário", required = true) Long id, SenhaRequestDTO dto);
}
