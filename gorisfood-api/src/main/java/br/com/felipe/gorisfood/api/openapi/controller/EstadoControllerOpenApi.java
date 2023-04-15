package br.com.felipe.gorisfood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.model.request.EstadoRequestDTO;
import br.com.felipe.gorisfood.api.model.response.EstadoResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

@Api(tags = "Estados")
@Tags({
	@Tag(name = "Estados", description = "Gerencia os estados")
})
public interface EstadoControllerOpenApi {

	@ApiOperation("Lista os estados")
	CollectionModel<EstadoResponseDTO> listar();
	
	@ApiOperation("Busca um estado por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID do estado inválido", 
					 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							 			schema = @Schema(implementation = Problema.class))),
		@ApiResponse(responseCode = "404", description = "Estado não encontrado", 
					 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
			 							schema = @Schema(implementation = Problema.class)))
	})
	EstadoResponseDTO buscar(@ApiParam(value = "ID do estado", example = "1") Long id);
	
	@ApiOperation("Cadastra um estado")
	@ApiResponse(responseCode = "201", description = "Estado cadastrado")
	EstadoResponseDTO criar(EstadoRequestDTO estadoDTO);
	
	@ApiOperation("Atualiza um estado por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Estado atualizado"),
		@ApiResponse(responseCode = "404", description = "Estado não encontrado", 
		 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = Problema.class)))
	})
	EstadoResponseDTO alterar(@ApiParam(value = "ID do estado", example = "1") Long id, EstadoRequestDTO estadoDTO);

	@ApiOperation("Exclui um estado por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Estado excluido"),
		@ApiResponse(responseCode = "404", description = "Estado não encontrado", 
		 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = Problema.class)))
	})
	void remover(@ApiParam(value = "ID do estado", example = "1") Long id);
}
