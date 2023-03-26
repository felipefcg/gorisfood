package br.com.felipe.gorisfood.api.controller.openapi;

import java.util.List;

import org.springframework.http.MediaType;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.model.request.CidadeRequestDTO;
import br.com.felipe.gorisfood.api.model.response.CidadeResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {
	
	@ApiOperation("Lista as cidades")
	public List<CidadeResponseDTO> listar();

	@ApiOperation("Busca uma cidade por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID da cidade inválido",  
					content = {@Content(schema = @Schema(implementation = Problema.class),
										mediaType = MediaType.APPLICATION_JSON_VALUE)}
		),
		@ApiResponse(responseCode = "404", description = "Cidade não encontrada", 
					 content = {@Content(schema = @Schema(implementation = Problema.class), 
					 					 mediaType = MediaType.APPLICATION_JSON_VALUE)}
		)
	})
	public CidadeResponseDTO buscar(@ApiParam(value = "ID de uma cidade", example = "1" ) Long id);
	
	@ApiOperation("Cadastra uma cidade")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Cidade cadastrada"),
		@ApiResponse(responseCode = "422", description = "ID do estado não cadastrado", 
					 content = {@Content(schema = @Schema(implementation = Problema.class), 
					 					 mediaType = MediaType.APPLICATION_JSON_VALUE)}
		)
	})
	public CidadeResponseDTO criar(@ApiParam(value = "Representação de uma nova cidade") CidadeRequestDTO cidadeDTO);
	
	@ApiOperation("Atualiza uma cidade por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Cidade atualizada"),
		@ApiResponse(responseCode = "404", description = "Cidade não encontrada", 
					content = {@Content(schema = @Schema(implementation = Problema.class), 
							   mediaType = MediaType.APPLICATION_JSON_VALUE)}
		),
		@ApiResponse(responseCode = "422", description = "ID do estado não cadastrado", 
					 content = {@Content(schema = @Schema(implementation = Problema.class), 
					 					 mediaType = MediaType.APPLICATION_JSON_VALUE)}
		)
	})
	public CidadeResponseDTO alterar(@ApiParam(value = "ID de uma cidade", example = "1") Long id, CidadeRequestDTO cidadeDTO);
	
	@ApiOperation(value = "Exclui uma cidade por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Cidade excluida"),
		@ApiResponse(responseCode = "404", description = "Cidade não encontrada", 
					 content = {@Content(schema = @Schema(implementation = Problema.class), 
										 mediaType = MediaType.APPLICATION_JSON_VALUE)}
		)
	})
	public void remover(@ApiParam(value = "ID de uma cidade", example = "1") Long id);
}
