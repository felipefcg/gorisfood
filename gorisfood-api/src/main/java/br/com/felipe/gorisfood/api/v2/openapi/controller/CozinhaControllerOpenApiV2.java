package br.com.felipe.gorisfood.api.v2.openapi.controller;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.v2.model.request.CozinhaRequestDTOV2;
import br.com.felipe.gorisfood.api.v2.model.response.CozinhaResponseDTOV2;
import br.com.felipe.gorisfood.domain.model.Cozinha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import springfox.documentation.annotations.ApiIgnore;

@Tag(name = "Cozinhas", description = "Gerencia as cozinhas")
@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApiV2 {

	@ApiOperation("Lista as cozinhas com paginação")
	PagedModel<CozinhaResponseDTOV2> listar (Pageable pagable);
	
	@ApiOperation("Busca uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID de cozinha no formato inválido",
					 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							 			schema = @Schema(implementation = Problema.class))
		),
		@ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
		 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				 			schema = @Schema(implementation = Problema.class))
		)
	})
	CozinhaResponseDTOV2 buscar (Long id);
	
	@ApiOperation("Cadastra uma cozinha")
	@ApiResponse(responseCode = "201", description = "Cozinha Cadastrada")
	CozinhaResponseDTOV2 criar(CozinhaRequestDTOV2 cozinhaDTO);
	
	@ApiOperation("Atualiza uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Cozinha atualizada"),
		@ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
		 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				 			schema = @Schema(implementation = Problema.class))
		)
	})
	CozinhaResponseDTOV2 alterar(Long id, CozinhaRequestDTOV2 cozinhaDTO);
	
	@ApiOperation("Exclui uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Cozinha excluida"),
		@ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
		 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				 			schema = @Schema(implementation = Problema.class))
		)
	})
	void remover(Long id);
	
	@ApiIgnore
	@ApiOperation("Busca a primeira cozinha cadastrada na aplicação - Testes")
	Optional<Cozinha> buscarPrimeiro();
}
