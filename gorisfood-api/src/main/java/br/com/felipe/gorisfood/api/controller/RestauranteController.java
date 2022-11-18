package br.com.felipe.gorisfood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.felipe.gorisfood.domain.exception.CozinhaNaoEncontradaException;
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
	public Restaurante buscar(@PathVariable Long id){
		return service.buscar(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante criar(@RequestBody Restaurante restaurante) {
		try {	
			return service.criar(restaurante);
		} catch (CozinhaNaoEncontradaException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
		}
	}

	@PutMapping("{id}")
	public Restaurante alterar(@PathVariable Long id, 
							   @RequestBody Restaurante restaurante) {
		try {
			return service.alterar(id, restaurante);
		} catch (CozinhaNaoEncontradaException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
		}
	}
	
	@PatchMapping("{id}")
	public Restaurante alterarParcialmente(@PathVariable Long id, 
							   @RequestBody Map<String, Object> campos) {
		try {
			return service.alterarParcialmente(id, campos);
		} catch (CozinhaNaoEncontradaException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
		}
	}
	
	@GetMapping(value = "por-nome", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<Restaurante> buscarPorNomeECozinha(String nome, Long cozinhaId) {
		return service.buscarPorNomeECozinha(nome, cozinhaId);
	}
	
	@GetMapping(value = "por-nome-e-taxa", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<Restaurante> buscarPorNomeETaxa(String nome, BigDecimal taxaInicio, BigDecimal taxaFim) {
		return service.buscarPorNomeETaxa(nome, taxaInicio, taxaFim);
	}
	
	@GetMapping(value = "por-nome-cozinha-e-taxa", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<Restaurante> buscarPorCozinhaETaxa(String nomeCozinha, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return service.buscarPorCozinhaETaxa(nomeCozinha, taxaInicial, taxaFinal);
	}
	
	@GetMapping(value = "por-frete-gratis-e-nome", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<Restaurante> buscarPorFreteGratisENome(String nome) {
		return service.buscarPorFreteGratisENome(nome);
	}
	
	@GetMapping(value = "primeiro", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public Optional<Restaurante> buscarPrimeiro() {
		return service.buscarPrimeiro();
	}
}
