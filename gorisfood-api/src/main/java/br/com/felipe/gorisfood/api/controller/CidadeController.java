package br.com.felipe.gorisfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.felipe.gorisfood.api.assembler.CidadeRequestDtoDesassembler;
import br.com.felipe.gorisfood.api.assembler.CidadeResponseDtoAssembler;
import br.com.felipe.gorisfood.api.exceptionhandler.Problema;
import br.com.felipe.gorisfood.api.exceptionhandler.TipoProblema;
import br.com.felipe.gorisfood.api.model.request.CidadeRequestDTO;
import br.com.felipe.gorisfood.api.model.response.CidadeResponseDTO;
import br.com.felipe.gorisfood.domain.exception.EstadoNaoEncontradoException;
import br.com.felipe.gorisfood.domain.model.Cidade;
import br.com.felipe.gorisfood.domain.service.CadastroCidadeService;

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
	
	@GetMapping(consumes = MediaType.ALL_VALUE)
	public List<CidadeResponseDTO> listar() {
		return assembler.toDtoList(service.listar());
	}
	
	@GetMapping(value = "{id}", consumes = MediaType.ALL_VALUE)
	public CidadeResponseDTO buscar(@PathVariable Long id) {
		return assembler.toDto(service.buscar(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeResponseDTO criar(@RequestBody @Valid CidadeRequestDTO cidadeDTO) {
		Cidade cidade = desassembler.toModel(cidadeDTO);
		return  assembler.toDto(service.salvar(cidade));
	}
	
	@PutMapping("{id}")
	public CidadeResponseDTO alterar(@PathVariable Long id, @RequestBody @Valid CidadeRequestDTO cidadeDTO) {
		Cidade cidadeAtual = service.buscar(id);
		desassembler.copyDtoToModel(cidadeDTO, cidadeAtual);
		return assembler.toDto(service.alterar(cidadeAtual));
	}
	
	@DeleteMapping(value = "{id}", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
			service.remover(id);
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
