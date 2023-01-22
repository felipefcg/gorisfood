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

import br.com.felipe.gorisfood.api.assembler.ProdutoRequestDtoDisassembler;
import br.com.felipe.gorisfood.api.assembler.ProdutoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.model.request.ProdutoRequestDTO;
import br.com.felipe.gorisfood.api.model.response.ProdutoResponseDTO;
import br.com.felipe.gorisfood.domain.service.CadastroProdutoService;
import br.com.felipe.gorisfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "restaurantes/{restauranteId}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoContoller {

	@Autowired
	private CadastroProdutoService service;
	
	@Autowired
	private CadastroRestauranteService restauranteService;
	
	@Autowired
	private ProdutoResponseDtoAssembler assembler;
	
	@Autowired
	private ProdutoRequestDtoDisassembler desassembler;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProdutoResponseDTO> listar(@PathVariable Long restauranteId){
		return assembler.toDtoList(service.listar(restauranteId));
	}
	
	@GetMapping("{produtoId}")
	public ProdutoResponseDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId){
		return assembler.toDto(service.buscar(produtoId, restauranteId));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoResponseDTO criar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoRequestDTO produtoDTO) {
		var produto = desassembler.toModel(produtoDTO);
		return assembler.toDto(service.criar(restauranteId, produto));
	
	}
	
	@PutMapping(value = "{produtoId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ProdutoResponseDTO alterar(@PathVariable Long restauranteId, @PathVariable Long produtoId, 
			@RequestBody @Valid ProdutoRequestDTO produtoDTO) {

		var produto = service.buscar(produtoId, restauranteId);
		
		desassembler.copyDtoToModel(produtoDTO, produto);
		return assembler.toDto(service.alterar(produto));
	}
	
	@DeleteMapping("{produtoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		service.remover(produtoId, restauranteId);
	}
	
}
