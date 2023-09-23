package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.v1.model.response.PermissaoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Grupos")
@SecurityRequirement(name = "security_auth")public interface GrupoPermissaoControllerOpenApi {
	
	@Operation(summary = "Lista as permissões associadas a um grupo", 
			description = "Lista as permissões associadas a um grupo",
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
	CollectionModel<PermissaoResponseDTO> listar(@Parameter(description = "ID do grupo", example = "1") Long grupoId);
	
	@Operation(summary = "Associação de permissão com  grupo", 
			description = "Associação de permissão com  grupo",
			responses = {
					@ApiResponse( responseCode = "204", description = "Associação efetuadada com sucesso"),
					@ApiResponse( responseCode = "404", description = "Grupo ou permissão não encontrado" , 
					  			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
						  				 schema = @Schema(implementation = Problema.class))
					)
			})
	ResponseEntity<Void> associar (@Parameter(description = "ID do grupo", example = "1") Long grupoId,
			@Parameter(description = "ID da permissão", example = "1") Long permissaoId);
	
	@Operation(summary = "Desassociação de permissão com  grupo", 
			description = "Desassociação de permissão com  grupo",
			responses = {
					@ApiResponse( responseCode = "204", description = "Desassociação efetuadada com sucesso"),
					@ApiResponse( responseCode = "404", description = "Grupo ou permissão não encontrado" , 
					  			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
						  				 schema = @Schema(implementation = Problema.class))
					)
			})
	ResponseEntity<Void> desassociar (@Parameter(description = "ID do grupo", example = "1") Long grupoId, 
			@Parameter(description = "ID da permissão", example = "1") Long permissaoId);
}
