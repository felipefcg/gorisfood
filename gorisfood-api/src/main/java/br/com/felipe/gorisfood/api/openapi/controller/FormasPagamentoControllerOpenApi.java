package br.com.felipe.gorisfood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.ServletWebRequest;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.model.request.FormaPagamentoRequestDTO;
import br.com.felipe.gorisfood.api.model.response.FormaPagamentoResponseDTO;
import br.com.felipe.gorisfood.api.openapi.model.FormasPagamentoModelOpenApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Formas de pagamento")
public interface FormasPagamentoControllerOpenApi {

	@ApiOperation(value = "Lista as formas de pagamento")
	@ApiResponse(responseCode = "200",
				 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = FormasPagamentoModelOpenApi.class))
	)
	ResponseEntity<CollectionModel<FormaPagamentoResponseDTO>> listar(ServletWebRequest request);
	
	@ApiOperation("Busca uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID de forma de pagamento no formato inválido",
					 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							 			schema = @Schema(implementation = Problema.class))
		),
		@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada",
		 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				 			schema = @Schema(implementation = Problema.class))
		)
	})
	ResponseEntity<FormaPagamentoResponseDTO> buscar(Long id, ServletWebRequest request);
	
	@ApiOperation("Cadastra uma forma de pagamento")
	@ApiResponse(responseCode = "201", description = "Forma de pagamento Cadastrada")
	FormaPagamentoResponseDTO criar(FormaPagamentoRequestDTO formaPagamentoDTO);
	
	@ApiOperation("Atualiza uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Forma de pagamento atualizada"),
		@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada",
		 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				 			schema = @Schema(implementation = Problema.class))
		)
	})
	FormaPagamentoResponseDTO alterar(Long id, FormaPagamentoRequestDTO formaPagamentoDTO);
	
	@ApiOperation("Exclui uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Forma de pagamento excluida"),
		@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada",
		 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				 			schema = @Schema(implementation = Problema.class))
		)
	})
	void remover(@PathVariable Long id);
}
