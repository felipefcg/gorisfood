package br.com.felipe.gorisfood.api.controller;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.felipe.gorisfood.domain.exception.CozinhaNaoEncontradaException;
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
	public Restaurante buscar(@PathVariable Long id){
		return service.buscar(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante criar(@RequestBody Restaurante restaurante) {
		try {	
			return service.criar(restaurante);
		} catch (CozinhaNaoEncontradaException e) {
			throw new EntidadeRelacionamentoNaoEncontradaException(e.getMessage());
		}
	}

	@PutMapping("{id}")
	public Restaurante alterar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
		try {
			return service.alterar(id, restaurante);
		} catch (CozinhaNaoEncontradaException e) {
			throw new EntidadeRelacionamentoNaoEncontradaException(e.getMessage());
		}
	}
	
	@PatchMapping("{id}")
	public Restaurante alterarParcialmente(@PathVariable Long id, @RequestBody Map<String, Object> campos,
			HttpServletRequest request) {
		
		Restaurante restauranteDestino = service.buscar(id);
		merge(campos, restauranteDestino, request);
		
		try {
			return service.alterarParcialmente(restauranteDestino);
		} catch (CozinhaNaoEncontradaException e) {
			throw new EntidadeRelacionamentoNaoEncontradaException(e.getMessage());
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
	
	private void merge (Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
		HttpInputMessage inputMessage = new ServletServerHttpRequest(request);
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			
			Restaurante restauranteOrigem = mapper.convertValue(dadosOrigem, Restaurante.class);
			
			dadosOrigem.forEach((nomeCampo, valorCampo) -> {
	
					Field field = ReflectionUtils.findField(Restaurante.class, nomeCampo);
					field.setAccessible(true);
					
					Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
					
					ReflectionUtils.setField(field, restauranteDestino, novoValor);
			});
		} catch (IllegalArgumentException e) {
			Throwable causaRaiz = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), causaRaiz, inputMessage);
		}
	}
}
