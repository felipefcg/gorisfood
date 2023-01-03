package br.com.felipe.gorisfood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.assembler.RestauranteOutputDtoAssembler;
import br.com.felipe.gorisfood.api.assembler.RestauranteInputDtoDeassembler;
import br.com.felipe.gorisfood.api.model.input.RestauranteInputDTO;
import br.com.felipe.gorisfood.api.model.output.RestauranteOutputDTO;
import br.com.felipe.gorisfood.domain.exception.CozinhaNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.EntidadeRelacionamentoNaoEncontradaException;
import br.com.felipe.gorisfood.domain.model.Cozinha;
import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "restaurantes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController {

	@Autowired
	private CadastroRestauranteService service;

	@Autowired
	private RestauranteOutputDtoAssembler restauranteOutputDtoAssembler;
	
	@Autowired
	private RestauranteInputDtoDeassembler restauranteInputDtoDeassembler;
	
	@GetMapping(consumes = MediaType.ALL_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<RestauranteOutputDTO> listar(){
		return restauranteOutputDtoAssembler.toDtoList(service.listar());
	}
	
	@GetMapping(value =  "{id}", consumes = MediaType.ALL_VALUE)
	public RestauranteOutputDTO buscar(@PathVariable Long id){
		return restauranteOutputDtoAssembler.toDTO(service.buscar(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteOutputDTO criar(@RequestBody @Valid RestauranteInputDTO restauranteDTO) {
		try {	
			Restaurante restaurante = restauranteInputDtoDeassembler.toDomain(restauranteDTO);
			return restauranteOutputDtoAssembler.toDTO(service.criar(restaurante));
		} catch (CozinhaNaoEncontradaException e) {
			throw new EntidadeRelacionamentoNaoEncontradaException(e.getMessage());
		}
	}

	@PutMapping("{id}")
	public RestauranteOutputDTO alterar(@PathVariable Long id, @RequestBody @Valid RestauranteInputDTO restauranteDTO) {
		try {
			Restaurante restaurante = restauranteInputDtoDeassembler.toDomain(restauranteDTO);
			return restauranteOutputDtoAssembler.toDTO(service.alterar(id, restaurante));
		} catch (CozinhaNaoEncontradaException e) {
			throw new EntidadeRelacionamentoNaoEncontradaException(e.getMessage());
		}
	}

	@GetMapping(value = "por-nome", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<RestauranteOutputDTO> buscarPorNomeECozinha(String nome, Long cozinhaId) {
		return restauranteOutputDtoAssembler.toDtoList(service.buscarPorNomeECozinha(nome, cozinhaId));
	}
	
	@GetMapping(value = "por-nome-e-taxa", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<RestauranteOutputDTO> buscarPorNomeETaxa(String nome, BigDecimal taxaInicio, BigDecimal taxaFim) {
		return restauranteOutputDtoAssembler.toDtoList(service.buscarPorNomeETaxa(nome, taxaInicio, taxaFim));
	}
	
	@GetMapping(value = "por-nome-cozinha-e-taxa", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<RestauranteOutputDTO> buscarPorCozinhaETaxa(String nomeCozinha, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteOutputDtoAssembler.toDtoList(service.buscarPorCozinhaETaxa(nomeCozinha, taxaInicial, taxaFinal));
	}
	
	@GetMapping(value = "por-frete-gratis-e-nome", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<RestauranteOutputDTO> buscarPorFreteGratisENome(String nome) {
		return restauranteOutputDtoAssembler.toDtoList(service.buscarPorFreteGratisENome(nome));
	}
	
	@GetMapping(value = "primeiro", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public Optional<RestauranteOutputDTO> buscarPrimeiro() {
		return restauranteOutputDtoAssembler.toDTO(service.buscarPrimeiro());
	}
}
