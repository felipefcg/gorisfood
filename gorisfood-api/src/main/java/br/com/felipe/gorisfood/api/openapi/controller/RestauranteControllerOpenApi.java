package br.com.felipe.gorisfood.api.openapi.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.model.request.RestauranteRequestDTO;
import br.com.felipe.gorisfood.api.model.response.RestauranteApenasNomeResponseDTO;
import br.com.felipe.gorisfood.api.model.response.RestauranteResponseDTO;
import br.com.felipe.gorisfood.api.model.response.RestauranteBasicoResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Restaurantes")
@Tag(name= "Restaurantes", description = "Endpoints de acesso às informações relacionadas ao restaurante")
public interface RestauranteControllerOpenApi {

	@ApiOperation(value = "Listar restaurantes")
	@ApiImplicitParam(value = "Nome da projeção de restaurantes", 
						name = "projecao", paramType = "query", dataTypeClass = String.class, allowableValues = "apenas-nome")
	CollectionModel<RestauranteBasicoResponseDTO> listar();
	
	@ApiOperation(value = "Listar restaurantes", hidden = true)
	CollectionModel<RestauranteApenasNomeResponseDTO> listarApenasNome();

	@ApiOperation(value = "Busca um restaurante por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID do restaurante inválido", 
					 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
					 					schema = @Schema(implementation = Problema.class))),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			 		 					schema = @Schema(implementation = Problema.class))})
	})	
	RestauranteResponseDTO buscar(@ApiParam(value = "ID do restaurante", example = "1") Long id);

	@ApiOperation(value = "Cadastra um restaurante")
	@ApiResponses ({
		@ApiResponse(responseCode = "201", description = "Restaurante criado"),
		@ApiResponse(responseCode = "422", description = "ID de uma das propriedades (cozinha ou cidade) inexistente no sistema", 
		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
		 					schema = @Schema(implementation = Problema.class))})
	})
	RestauranteResponseDTO criar(RestauranteRequestDTO restauranteDTO);

	@ApiOperation(value = "Atualiza um restaurante por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Restaurante atualizado"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			 		 					schema = @Schema(implementation = Problema.class))}),
		@ApiResponse(responseCode = "422", description = "ID de uma das propriedades (cozinha ou cidade) inexistente no sistema", 
		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
		 					schema = @Schema(implementation = Problema.class))})
	})
	RestauranteResponseDTO alterar(@ApiParam(value = "ID do restaurante", example = "1") Long id, RestauranteRequestDTO restauranteDTO);
		
	@ApiOperation(value = "Ativa um restaurante por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurante ativado"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			 		 					schema = @Schema(implementation = Problema.class))})
	})
	ResponseEntity<Void> ativar(@ApiParam(value = "ID do restaurante", example = "1") Long id);
	
	@ApiOperation(value = "Inativa um restaurante por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurante inativado"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			 		 					schema = @Schema(implementation = Problema.class))})
	})
	ResponseEntity<Void> inativar(@ApiParam(value = "ID do restaurante") Long id);
	
	@ApiOperation(value = "Ativa múltiplos restaurantes")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurantes ativados"),
		@ApiResponse(responseCode = "422", description = "ID de restaurante informado inexistente no sistema", 
				 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
				 					schema = @Schema(implementation = Problema.class))})
	})
	ResponseEntity<Void> ativarMultiplos(@ApiParam(value = "Lista de IDs de restaurante") List<Long> id);
	
	@ApiOperation(value = "Inativa múltiplos restaurantes")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurantes inativados"),
		@ApiResponse(responseCode = "422", description = "ID de restaurante informado inexistente no sistema", 
				 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
				 					schema = @Schema(implementation = Problema.class))})
	})
	ResponseEntity<Void> inativarMultiplos(@ApiParam(value = "Lista de IDs de restaurante") List<Long> id);
	
	@ApiOperation(value = "Abre um restaurante por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurante aberto"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			 		 					schema = @Schema(implementation = Problema.class))})
	})
	ResponseEntity<Void> abrir(@ApiParam(value = "ID do restaurante", example = "1") Long id);
	
	@ApiOperation(value = "Fecha um restaurante por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurante fechado"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado", 
			 		 content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			 		 					schema = @Schema(implementation = Problema.class))})
	})
	ResponseEntity<Void> fechar(@ApiParam(value = "ID do restaurante", example = "1") Long id);
	
	
	//Endpoints ignorados para continuidade do estudo, deixados habilitados apenas para exemplificação de implementações JPA
//	@ApiIgnore(value = "Pois esse endpoint foi criado apenas para testes e exemplificação de implementações JPA")
//	CollectionModel<RestauranteResponseDTO> buscarPorNomeECozinha(String nome, Long cozinhaId);
//
//	@ApiIgnore(value = "Pois esse endpoint foi criado apenas para testes e exemplificação de implementações JPA")
//	List<RestauranteResponseDTO> buscarPorNomeETaxa(String nome, BigDecimal taxaInicio, BigDecimal taxaFim);
//	
//	@ApiIgnore(value = "Pois esse endpoint foi criado apenas para testes e exemplificação de implementações JPA")
//	List<RestauranteResponseDTO> buscarPorCozinhaETaxa(String nomeCozinha, BigDecimal taxaInicial, BigDecimal taxaFinal);
//
//	@ApiIgnore(value = "Pois esse endpoint foi criado apenas para testes e exemplificação de implementações JPA")
//	List<RestauranteResponseDTO> buscarPorFreteGratisENome(String nome);
//
//	@ApiIgnore(value = "Pois esse endpoint foi criado apenas para testes e exemplificação de implementações JPA")
//	Optional<RestauranteResponseDTO> buscarPrimeiro();	

}
