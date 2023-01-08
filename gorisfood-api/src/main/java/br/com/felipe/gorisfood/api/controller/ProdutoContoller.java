package br.com.felipe.gorisfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.assembler.ProdutoRequestDtoDesassembler;
import br.com.felipe.gorisfood.api.assembler.ProdutoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.model.request.ProdutoRequestDTO;
import br.com.felipe.gorisfood.api.model.response.ProdutoResponseDTO;
import br.com.felipe.gorisfood.domain.exception.EntidadeRelacionamentoNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.RestauranteNaoEncontradoException;
import br.com.felipe.gorisfood.domain.service.CadastroProdutoService;

@RestController
@RequestMapping(value = "produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoContoller {

	@Autowired
	private CadastroProdutoService service;
	
	@Autowired
	private ProdutoResponseDtoAssembler assembler;
	
	@Autowired
	private ProdutoRequestDtoDesassembler desassembler;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProdutoResponseDTO> listar(){
		return assembler.toDtoList(service.listar());
	}
	
	@GetMapping("{id}")
	public ProdutoResponseDTO buscar(@PathVariable Long id){
		return assembler.toDto(service.buscar(id));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoResponseDTO criar(@RequestBody @Valid ProdutoRequestDTO produtoDTO) {
		try {
			return assembler.toDto(service.criar(desassembler.toModel(produtoDTO)));
		} catch (RestauranteNaoEncontradoException e) {
			throw new EntidadeRelacionamentoNaoEncontradaException(e.getMessage());
		}
	}
	
	@PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ProdutoResponseDTO alterar(@PathVariable Long id, @RequestBody @Valid ProdutoRequestDTO produtoDTO) {
		try {
			var produto = service.buscar(id);
			desassembler.copyDtoToModel(produtoDTO, produto);
			return assembler.toDto(service.alterar(produto));
		} catch (RestauranteNaoEncontradoException e) {
			throw new EntidadeRelacionamentoNaoEncontradaException(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		service.remover(id);
	}
	
}
