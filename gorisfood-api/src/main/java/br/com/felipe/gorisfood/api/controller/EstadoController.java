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

import br.com.felipe.gorisfood.domain.exception.EntidadeEmUsoExcpetion;
import br.com.felipe.gorisfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.model.Estado;
import br.com.felipe.gorisfood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(value = "estados", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController {

	@Autowired
	private CadastroEstadoService service;
	
	@GetMapping(consumes = MediaType.ALL_VALUE)
	public List<Estado> listar() {
		return service.listar();
	}
	
	@GetMapping(value = "{id}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		try {
			Estado estado = service.buscar(id);
			return ResponseEntity.ok(estado);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Estado criar(@RequestBody Estado estado) {
		return service.salvar(estado);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> alterar(@PathVariable Long id, @RequestBody Estado estado){
		try {
			estado = service.atualizar(id, estado);
			return ResponseEntity.ok(estado);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} 
	}
	
	@DeleteMapping(value = "{id}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<Object> remover(@PathVariable Long id) {
		try {
			service.remover(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (EntidadeEmUsoExcpetion e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
