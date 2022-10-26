package br.com.felipe.gorisfood.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.EntidadeRelacionamentoNaoEncontradaException;
import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "restaurantes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController {

	@Autowired
	private CadastroRestauranteService service;

	@GetMapping(consumes = MediaType.ALL_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Restaurante> listar(){
		return service.listar();
	}
	
	@GetMapping(value =  "{id}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id){
		try {
			return ResponseEntity.ok(service.buscar(id));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> criar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = service.criar(restaurante);
			String url = String.format("http://localhost:8080/restaurantes/%d", restaurante.getId());
			
			return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, url).body(restaurante);
		} catch (EntidadeRelacionamentoNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).build();
//			return ResponseEntity.unprocessableEntity().build();
		}
		
	}

	@PutMapping("{id}")
	public ResponseEntity<?> alterar(@PathVariable Long id, 
							   @RequestBody Restaurante restaurante) {
		try {
			restaurante = service.alterar(id, restaurante);
			return ResponseEntity.ok(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (EntidadeRelacionamentoNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		}
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<Object> alterarParcialmente(@PathVariable Long id, 
							   @RequestBody Map<String, Object> campos) {
		
		try {
			Restaurante restaurante = service.alterarParcialmente(id, campos);
			return ResponseEntity.ok(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (EntidadeRelacionamentoNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		}
	}
}
