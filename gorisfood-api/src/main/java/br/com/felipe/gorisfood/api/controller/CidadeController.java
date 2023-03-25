package br.com.felipe.gorisfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.assembler.CidadeRequestDtoDesassembler;
import br.com.felipe.gorisfood.api.assembler.CidadeResponseDtoAssembler;
import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.exceptionhandler.TipoProblema;
import br.com.felipe.gorisfood.api.model.request.CidadeRequestDTO;
import br.com.felipe.gorisfood.api.model.response.CidadeResponseDTO;
import br.com.felipe.gorisfood.domain.exception.EstadoNaoEncontradoException;
import br.com.felipe.gorisfood.domain.model.Cidade;
import br.com.felipe.gorisfood.domain.service.CadastroCidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@Api(tags = "Cidades")
@RestController
@RequestMapping(value = "cidades",
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController {

	@Autowired
	private CadastroCidadeService service;
	
	@Autowired
	private CidadeResponseDtoAssembler assembler;
	
	@Autowired
	private CidadeRequestDtoDesassembler desassembler;
	
	@ApiOperation("Lista as cidades")
	@GetMapping(consumes = MediaType.ALL_VALUE)
	public List<CidadeResponseDTO> listar() {
		return assembler.toDtoList(service.listar());
	}

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
	@GetMapping(value = "{id}", consumes = MediaType.ALL_VALUE)
	public CidadeResponseDTO buscar(@ApiParam(value = "ID de uma cidade", example = "1" ) @PathVariable Long id) {
		return assembler.toDto(service.buscar(id));
	}
	
	@ApiOperation("Cadastra uma cidade")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Cidade cadastrada"),
		@ApiResponse(responseCode = "422", description = "ID do estado não cadastrado", 
					 content = {@Content(schema = @Schema(implementation = Problema.class), 
					 					 mediaType = MediaType.APPLICATION_JSON_VALUE)}
		)
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeResponseDTO criar(@ApiParam(value = "Representação de uma nova cidade") @RequestBody @Valid CidadeRequestDTO cidadeDTO) {
		Cidade cidade = desassembler.toModel(cidadeDTO);
		return  assembler.toDto(service.salvar(cidade));
	}
	
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
	@PutMapping("{id}")
	public CidadeResponseDTO alterar(@ApiParam(value = "ID de uma cidade", example = "1") @PathVariable Long id, 
									 @RequestBody @Valid CidadeRequestDTO cidadeDTO) {
		Cidade cidadeAtual = service.buscar(id);
		desassembler.copyDtoToModel(cidadeDTO, cidadeAtual);
		return assembler.toDto(service.alterar(cidadeAtual));
	}
	
	@ApiOperation(value = "Exclui uma cidade por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Cidade excluida"),
		@ApiResponse(responseCode = "404", description = "Cidade não encontrada", 
					 content = {@Content(schema = @Schema(implementation = Problema.class), 
										 mediaType = MediaType.APPLICATION_JSON_VALUE)}
		)
	})
	@DeleteMapping(value = "{id}", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> remover(@ApiParam(value = "ID de uma cidade", example = "1") @PathVariable Long id) {
			service.remover(id);
			return ResponseEntity.noContent().build();
	}
	
	@ExceptionHandler(EstadoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public Problema handleEstadoNaoEncontradoException(EstadoNaoEncontradoException e) {
		TipoProblema tipoProblema = TipoProblema.ENTIDADE_RELACIONAMENTO_NAO_ENCONTRADA;
		return Problema.builder()
				.status(HttpStatus.UNPROCESSABLE_ENTITY.value())
				.tipo(tipoProblema.getUri())
				.titulo(tipoProblema.getTitulo())
				.detalhe(e.getMessage())
				.build();
	}
}
