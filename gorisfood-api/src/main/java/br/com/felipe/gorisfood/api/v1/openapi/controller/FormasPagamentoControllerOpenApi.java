package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.v1.model.request.FormaPagamentoRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.FormaPagamentoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Formas de Pagamento")
@SecurityRequirement(name = "security_auth")public interface FormasPagamentoControllerOpenApi {
	
	@Operation(summary = "Lista as formas de pagamento", 
			description = "Retorna todas as formas de pagamente disponíveis")
	ResponseEntity<CollectionModel<FormaPagamentoResponseDTO>> listar(ServletWebRequest request);
	
	@Operation(summary = "Busca uma forma de pagamento por ID", 
			description = "Busca uma forma de pagamento por ID",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse(responseCode = "400", description = "ID de forma de pagamento no formato inválido",
								 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
										 			schema = @Schema(implementation = Problema.class))
					),
					@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada",
					 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							 			schema = @Schema(implementation = Problema.class))
					)	
			})
	ResponseEntity<FormaPagamentoResponseDTO> buscar(@Parameter(description = "ID da forma de pagamento", example = "1") Long id, 
			ServletWebRequest request);
	
	@Operation(summary = "Cadastra uma forma de pagamento", 
			description = "Cadastra uma nova forma de pagamento",
			responses = @ApiResponse(responseCode = "201", description = "Forma de pagamento Cadastrada"))
	FormaPagamentoResponseDTO criar(@RequestBody FormaPagamentoRequestDTO formaPagamentoDTO);
	
	@Operation(summary = "Atualiza uma forma de pagamento por ID", 
			description = "Atualiza dados da forma de pagamento",
			responses = {
					@ApiResponse(responseCode = "200", description = "Forma de pagamento atualizada"),
					@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada",
					 	content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							 			schema = @Schema(implementation = Problema.class)))
			})
	FormaPagamentoResponseDTO alterar(@Parameter(description = "ID da forma de pagamento", example = "1") Long id, 
			@RequestBody FormaPagamentoRequestDTO formaPagamentoDTO);
	
	@Operation(summary = "Exclui uma forma de pagamento por ID", 
			description = "Exclui uma forma de pagamento por ID",
			responses = {
					@ApiResponse(responseCode = "204", description = "Forma de pagamento excluida"),
					@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada",
					 	content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							 			schema = @Schema(implementation = Problema.class))
					)
			})
	void remover(@Parameter(description = "ID da forma de pagamento", example = "1") Long id);
}
