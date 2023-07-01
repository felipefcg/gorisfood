package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.v1.model.response.PermissaoResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {

	@ApiOperation("Lista as permissões associadas a um grupo")
	@ApiResponses ({
		@ApiResponse( responseCode = "400", description = "ID do grupo inválido" , 
					  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
						  				 schema = @Schema(implementation = Problema.class))
		),
		@ApiResponse( responseCode = "404", description = "Grupo não encontrado" , 
		  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			  				 schema = @Schema(implementation = Problema.class))
		)
	})
	CollectionModel<PermissaoResponseDTO> listar(@ApiParam(value = "ID do grupo") Long grupoId);
	
	@ApiOperation("Associação de permissão com  grupo")
	@ApiResponses ({
		@ApiResponse( responseCode = "204", description = "Associação efetuadada com sucesso" 	),
		@ApiResponse( responseCode = "404", description = "Grupo ou permissão não encontrado" , 
		  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			  				 schema = @Schema(implementation = Problema.class))
		)
	})
	ResponseEntity<Void> associar (@ApiParam(value = "ID do grupo") Long grupoId, @ApiParam(value = "ID do grupo") Long permissaoId);
	

	@ApiOperation("Desassociação de permissão com  grupo")
	@ApiResponses ({
		@ApiResponse( responseCode = "204", description = "Desassociação efetuadada com sucesso" 	),
		@ApiResponse( responseCode = "404", description = "Grupo ou permissão não encontrado" , 
		  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			  				 schema = @Schema(implementation = Problema.class))
		)
	})
	ResponseEntity<Void> desassociar (@ApiParam(value = "ID do grupo") Long grupoId, @ApiParam(value = "ID do grupo") Long permissaoId);
}