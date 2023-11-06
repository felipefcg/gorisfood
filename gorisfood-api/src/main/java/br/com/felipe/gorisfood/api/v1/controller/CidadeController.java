package br.com.felipe.gorisfood.api.v1.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import br.com.felipe.gorisfood.api.ResourceUriHelper;
import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.exceptionhandler.TipoProblema;
import br.com.felipe.gorisfood.api.v1.assembler.CidadeRequestDtoDesassembler;
import br.com.felipe.gorisfood.api.v1.assembler.CidadeResponseDtoAssembler;
import br.com.felipe.gorisfood.api.v1.model.request.CidadeRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.CidadeResponseDTO;
import br.com.felipe.gorisfood.api.v1.openapi.controller.CidadeControllerOpenApi;
import br.com.felipe.gorisfood.core.security.CheckSecurity;
import br.com.felipe.gorisfood.domain.exception.EstadoNaoEncontradoException;
import br.com.felipe.gorisfood.domain.model.Cidade;
import br.com.felipe.gorisfood.domain.service.CadastroCidadeService;


@RestController
@RequestMapping(path = "v1/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

	@Autowired
	private CadastroCidadeService service;
	
	@Autowired
	private CidadeResponseDtoAssembler assembler;
	
	@Autowired
	private CidadeRequestDtoDesassembler desassembler;

	@CheckSecurity.Cidades.PodeConsultar
	@Deprecated
	@GetMapping
	public CollectionModel<CidadeResponseDTO> listar() {
		 return assembler.toCollectionModel(service.listar());
	}

	@CheckSecurity.Cidades.PodeConsultar
	@GetMapping(value = "{id}")
	public CidadeResponseDTO buscar(@PathVariable Long id) {
		return assembler.toModel(service.buscar(id));
	}
	
	@CheckSecurity.Cidades.PodeEditar
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeResponseDTO criar(@RequestBody @Valid CidadeRequestDTO cidadeDTO) {
		Cidade cidade = desassembler.toModel(cidadeDTO);
		CidadeResponseDTO cidadeDto = assembler.toModel(service.salvar(cidade));
		
		ResourceUriHelper.addUriInResponseHeader(cidadeDto.getId());
		return cidadeDto;
	}
	
	@CheckSecurity.Cidades.PodeEditar
	@PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public CidadeResponseDTO alterar(@PathVariable Long id, 
									 @RequestBody @Valid CidadeRequestDTO cidadeDTO) {
		Cidade cidadeAtual = service.buscar(id);
		desassembler.copyDtoToModel(cidadeDTO, cidadeAtual);
		return assembler.toModel(service.alterar(cidadeAtual));
	}
	
	@CheckSecurity.Cidades.PodeEditar
	@DeleteMapping(value = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
			service.remover(id);
//			return ResponseEntity.noContent().build();
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
