package br.com.felipe.gorisfood.api.openapi.controller;

import java.util.List;

import org.springframework.http.MediaType;

import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.model.request.GrupoRequestDTO;
import br.com.felipe.gorisfood.api.model.response.GrupoResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Api(tags = "Grupos")
@Tag(name = "Grupos", description = "Gerencia os grupos de usuário")
public interface GrupoControllerOpenApi {

	@ApiOperation("Lista os grupos de usuários")
	List<GrupoResponseDTO> listar();
	
	@ApiOperation("Busca o grupo por ID")
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
	GrupoResponseDTO buscar(Long id);
	
	@ApiOperation("Exclui um grupo por ID")
	@ApiResponses ({
		@ApiResponse( responseCode = "204", description = "Grupo excluido" 	),
		@ApiResponse( responseCode = "400", description = "ID do grupo inválido" , 
					  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
						  				 schema = @Schema(implementation = Problema.class))
		),
		@ApiResponse( responseCode = "404", description = "Grupo não encontrado" , 
		  content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
			  				 schema = @Schema(implementation = Problema.class))
		)
	})
	void excluir(Long id);
	
	@ApiOperation("Cadastra um novo grupo")
	@ApiResponses ({
		@ApiResponse( responseCode = "201", description = "Grupo cadastrado")
	})
	GrupoResponseDTO criar(GrupoRequestDTO dto) ;

	@ApiOperation("Atualiza um grupo por ID")
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
	GrupoResponseDTO alterar(Long id,  GrupoRequestDTO dto);
}
