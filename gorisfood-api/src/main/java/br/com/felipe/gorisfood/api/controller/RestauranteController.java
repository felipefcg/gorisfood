package br.com.felipe.gorisfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.model.Cozinha;
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

}
