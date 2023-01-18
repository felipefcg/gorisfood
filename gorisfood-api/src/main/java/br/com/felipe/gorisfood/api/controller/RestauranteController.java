package br.com.felipe.gorisfood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

import br.com.felipe.gorisfood.api.assembler.RestauranteRequestDtoDesassembler;
import br.com.felipe.gorisfood.api.assembler.RestauranteResponseDtoAssembler;
import br.com.felipe.gorisfood.api.model.request.RestauranteRequestDTO;
import br.com.felipe.gorisfood.api.model.response.RestauranteResponseDTO;
import br.com.felipe.gorisfood.domain.exception.CidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.CozinhaNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.EntidadeRelacionamentoNaoEncontradaException;
import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "restaurantes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController {

	@Autowired
	private CadastroRestauranteService service;

	@Autowired
	private RestauranteResponseDtoAssembler restauranteResponseDtoAssembler;
	
	@Autowired
	private RestauranteRequestDtoDesassembler restauranteRequestDtoDesassembler;
	
	@GetMapping(consumes = MediaType.ALL_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<RestauranteResponseDTO> listar(){
		return restauranteResponseDtoAssembler.toDtoList(service.listar());
	}
	
	@GetMapping(value =  "{id}", consumes = MediaType.ALL_VALUE)
	public RestauranteResponseDTO buscar(@PathVariable Long id){
		return restauranteResponseDtoAssembler.toDTO(service.buscar(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteResponseDTO criar(@RequestBody @Valid RestauranteRequestDTO restauranteDTO) {
		try {	
			Restaurante restaurante = restauranteRequestDtoDesassembler.toDomain(restauranteDTO);
			return restauranteResponseDtoAssembler.toDTO(service.criar(restaurante));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new EntidadeRelacionamentoNaoEncontradaException(e.getMessage());
		}
	}

	@PutMapping("{id}")
	public RestauranteResponseDTO alterar(@PathVariable Long id, @RequestBody @Valid RestauranteRequestDTO restauranteDTO) {
		try {
			Restaurante restaurante = service.buscar(id);
			restauranteRequestDtoDesassembler.copyToDomain(restauranteDTO, restaurante);
			return restauranteResponseDtoAssembler.toDTO(service.alterar(restaurante));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new EntidadeRelacionamentoNaoEncontradaException(e.getMessage());
		}
	}

	@GetMapping(value = "por-nome", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<RestauranteResponseDTO> buscarPorNomeECozinha(String nome, Long cozinhaId) {
		return restauranteResponseDtoAssembler.toDtoList(service.buscarPorNomeECozinha(nome, cozinhaId));
	}
	
	@GetMapping(value = "por-nome-e-taxa", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<RestauranteResponseDTO> buscarPorNomeETaxa(String nome, BigDecimal taxaInicio, BigDecimal taxaFim) {
		return restauranteResponseDtoAssembler.toDtoList(service.buscarPorNomeETaxa(nome, taxaInicio, taxaFim));
	}
	
	@GetMapping(value = "por-nome-cozinha-e-taxa", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<RestauranteResponseDTO> buscarPorCozinhaETaxa(String nomeCozinha, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteResponseDtoAssembler.toDtoList(service.buscarPorCozinhaETaxa(nomeCozinha, taxaInicial, taxaFinal));
	}
	
	@GetMapping(value = "por-frete-gratis-e-nome", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<RestauranteResponseDTO> buscarPorFreteGratisENome(String nome) {
		return restauranteResponseDtoAssembler.toDtoList(service.buscarPorFreteGratisENome(nome));
	}
	
	@GetMapping(value = "primeiro", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public Optional<RestauranteResponseDTO> buscarPrimeiro() {
		return restauranteResponseDtoAssembler.toDTO(service.buscarPrimeiro());
	}
	
	@PutMapping(value = "{id}/ativo", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long id) {
		try {
			service.ativar(id);
		} catch (CozinhaNaoEncontradaException e) {
			throw new EntidadeRelacionamentoNaoEncontradaException(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "{id}/ativo", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long id) {
		try {
			service.inativar(id);
		} catch (CozinhaNaoEncontradaException e) {
			throw new EntidadeRelacionamentoNaoEncontradaException(e.getMessage());
		}
	}
}
