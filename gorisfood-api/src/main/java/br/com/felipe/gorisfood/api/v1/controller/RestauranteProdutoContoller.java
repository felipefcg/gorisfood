package br.com.felipe.gorisfood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.assembler.ProdutoRequestDtoDisassembler;
import br.com.felipe.gorisfood.api.v1.assembler.ProdutoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.v1.model.request.ProdutoRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.ProdutoResponseDTO;
import br.com.felipe.gorisfood.api.v1.openapi.controller.RestauranteProdutoContollerOpenApi;
import br.com.felipe.gorisfood.domain.model.Produto;
import br.com.felipe.gorisfood.domain.service.CadastroProdutoService;

@RestController
@RequestMapping(value = "v1/restaurantes/{restauranteId}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoContoller implements RestauranteProdutoContollerOpenApi {

	@Autowired
	private CadastroProdutoService service;
	
	@Autowired
	private ProdutoResponseDtoAssembler assembler;
	
	@Autowired
	private ProdutoRequestDtoDisassembler desassembler;
	
	@Autowired
	private GorisLinks gorisLinks;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CollectionModel<ProdutoResponseDTO> listar(@PathVariable Long restauranteId, @RequestParam(required = false, defaultValue = "false") Boolean incluirInativos){
		
		return assembler.toCollectionModel(service.listar(restauranteId, incluirInativos), restauranteId);
	}
	
	@GetMapping("{produtoId}")
	public ProdutoResponseDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId){
		Produto produto = service.buscar(produtoId, restauranteId);
		return assembler.toModel(produto)
				.add(gorisLinks.linkToProdutoFoto(produto.getRestaurante().getId(), produto.getId(), "foto"));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoResponseDTO criar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoRequestDTO produtoDTO) {
		var produto = desassembler.toModel(produtoDTO);
		return assembler.toModel(service.criar(restauranteId, produto));
	
	}
	
	@PutMapping(value = "{produtoId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ProdutoResponseDTO alterar(@PathVariable Long restauranteId, @PathVariable Long produtoId, 
			@RequestBody @Valid ProdutoRequestDTO produtoDTO) {

		var produto = service.buscar(produtoId, restauranteId);
		
		desassembler.copyDtoToModel(produtoDTO, produto);
		return assembler.toModel(service.alterar(produto));
	}
	
	@DeleteMapping("{produtoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		service.remover(produtoId, restauranteId);
	}
	
}
