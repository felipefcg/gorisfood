package br.com.felipe.gorisfood.api.v2.controller;

import javax.validation.Valid;

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
import br.com.felipe.gorisfood.api.v2.assembler.CidadeRequestDtoDesassemblerV2;
import br.com.felipe.gorisfood.api.v2.assembler.CidadeResponseDtoAssemblerV2;
import br.com.felipe.gorisfood.api.v2.model.request.CidadeRequestDTOV2;
import br.com.felipe.gorisfood.api.v2.model.response.CidadeResponseDTOV2;
import br.com.felipe.gorisfood.api.v2.openapi.controller.CidadeControllerOpenApiV2;
import br.com.felipe.gorisfood.core.security.CheckSecurity;
import br.com.felipe.gorisfood.domain.exception.EstadoNaoEncontradoException;
import br.com.felipe.gorisfood.domain.model.Cidade;
import br.com.felipe.gorisfood.domain.service.CadastroCidadeService;


@RestController
@RequestMapping(path = "v2/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeControllerV2 implements CidadeControllerOpenApiV2 {

	@Autowired
	private CadastroCidadeService service;
	
	@Autowired
	private CidadeResponseDtoAssemblerV2 assembler;
	
	@Autowired
	private CidadeRequestDtoDesassemblerV2 desassembler;
	
	@CheckSecurity.Cidades.PodeConsultar
	@GetMapping
	public CollectionModel<CidadeResponseDTOV2> listar() {
		 return assembler.toCollectionModel(service.listar());
	}

	@CheckSecurity.Cidades.PodeConsultar
	@GetMapping(value = "{id}")
	public CidadeResponseDTOV2 buscar(@PathVariable Long id) {
		return assembler.toModel(service.buscar(id));
	}
	
	@CheckSecurity.Cidades.PodeEditar
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeResponseDTOV2 criar(@RequestBody @Valid CidadeRequestDTOV2 cidadeDTO) {
		Cidade cidade = desassembler.toModel(cidadeDTO);
		CidadeResponseDTOV2 cidadeDto = assembler.toModel(service.salvar(cidade));
		
		ResourceUriHelper.addUriInResponseHeader(cidadeDto.getIdCidade());
		return cidadeDto;
	}
	
	@CheckSecurity.Cidades.PodeEditar
	@PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public CidadeResponseDTOV2 alterar(@PathVariable Long id, 
									 @RequestBody @Valid CidadeRequestDTOV2 cidadeDTO) {
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
