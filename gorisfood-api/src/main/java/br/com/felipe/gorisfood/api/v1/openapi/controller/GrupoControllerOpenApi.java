package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.v1.model.request.GrupoRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.GrupoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Grupos")
@SecurityRequirement(name = "security_auth")public interface GrupoControllerOpenApi {

	@Operation(summary = "Lista os grupos de usuários", 
			description = "Lista os grupos de usuários")
	CollectionModel<GrupoResponseDTO> listar();
	
	@Operation(summary = "Busca o grupo por ID", 
			description = "Busca o grupo por ID",
			responses = {
				@ApiResponse( responseCode = "200"),
				@ApiResponse( responseCode = "400", description = "ID do grupo inválido" , 
							  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
								  				 schema = @Schema(implementation = Problema.class))
				),
				@ApiResponse( responseCode = "404", description = "Grupo não encontrado" , 
				  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
					  				 schema = @Schema(implementation = Problema.class))
				)}
	)
	GrupoResponseDTO buscar(@Parameter(description = "ID do grupo", example = "1") Long id);
	
	@Operation(summary = "Exclui um grupo por ID", 
			description = "Exclui um grupo por ID",
			responses = {
					@ApiResponse( responseCode = "204", description = "Grupo excluido"),
					@ApiResponse( responseCode = "400", description = "ID do grupo inválido" , 
				  				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
						  				 schema = @Schema(implementation = Problema.class))),
					@ApiResponse( responseCode = "404", description = "Grupo não encontrado" , 
								content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
						  				 schema = @Schema(implementation = Problema.class))
					)
			}
	)
	void excluir(@Parameter(description = "ID do grupo", example = "1") Long id);
	
	@Operation(summary = "Cadastra um novo grupo", 
			description = "Cadastra um novo grupo",
			responses = @ApiResponse( responseCode = "201", description = "Grupo cadastrado"))
	GrupoResponseDTO criar(@RequestBody(description = "Representação de um novo grupo") GrupoRequestDTO dto) ;
	
	@Operation(summary = "Atualiza um grupo por ID", 
			description = "Atualiza um grupo por ID",
			responses = {
					@ApiResponse(responseCode = "200"),
					@ApiResponse( responseCode = "400", description = "ID do grupo inválido" , 
								content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
								  				 schema = @Schema(implementation = Problema.class))
					),
					@ApiResponse( responseCode = "404", description = "Grupo não encontrado" , 
					  			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
						  				 schema = @Schema(implementation = Problema.class))
					)
			})
	GrupoResponseDTO alterar(@Parameter(description = "ID do grupo", example = "1") Long id,  
			@RequestBody(description = "Representação de um novo grupo") GrupoRequestDTO dto);
}
