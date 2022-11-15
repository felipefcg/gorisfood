package br.com.felipe.gorisfood.api.controller;

import java.util.List;

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

import br.com.felipe.gorisfood.domain.model.Produto;
import br.com.felipe.gorisfood.domain.service.CadastroProdutoService;

@RestController
@RequestMapping(value = "produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoContoller {

	@Autowired
	private CadastroProdutoService service;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Produto> listar(){
		return service.listar();
	}
	
	@GetMapping("{id}")
	public Produto buscar(@PathVariable Long id){
		return service.buscar(id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Produto criar(@RequestBody Produto novoProduto) {
		return service.criar(novoProduto);
	}
	
	@PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Produto alterar(@PathVariable Long id, @RequestBody Produto produtoAlterado) {
		return service.alterar(id,produtoAlterado);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		service.remover(id);
	}
	
}
