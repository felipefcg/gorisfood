package br.com.felipe.gorisfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.EntidadeRelacionamentoNaoEncontradaException;
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
	public ResponseEntity<Produto> buscar(@PathVariable Long id){
		try {
			return ResponseEntity.ok(service.buscar(id));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> criar(@RequestBody Produto novoProduto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(service.criar(novoProduto)); 
		} catch (EntidadeRelacionamentoNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body(e.getMessage());
		}
	}
	
	@PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> alterar(@PathVariable Long id, @RequestBody Produto produtoAlterado) {
		try {
			return ResponseEntity.ok(service.alterar(id,produtoAlterado));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (EntidadeRelacionamentoNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> remover(@PathVariable Long id) {
		try {
			service.remover(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
