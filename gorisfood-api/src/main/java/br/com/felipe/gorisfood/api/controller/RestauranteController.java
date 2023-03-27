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

import com.fasterxml.jackson.annotation.JsonView;

import br.com.felipe.gorisfood.api.assembler.RestauranteRequestDtoDesassembler;
import br.com.felipe.gorisfood.api.assembler.RestauranteResponseDtoAssembler;
import br.com.felipe.gorisfood.api.model.request.RestauranteRequestDTO;
import br.com.felipe.gorisfood.api.model.response.RestauranteResponseDTO;
import br.com.felipe.gorisfood.api.model.view.RestauranteView;
import br.com.felipe.gorisfood.api.openapi.controller.RestauranteControllerOpenApi;
import br.com.felipe.gorisfood.domain.exception.CidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.CozinhaNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.EntidadeRelacionamentoNaoEncontradaException;
import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.service.CadastroRestauranteService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

@RestController
@RequestMapping(value = "restaurantes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController implements RestauranteControllerOpenApi {
	
	@Autowired
	private CadastroRestauranteService service;

	@Autowired
	private RestauranteResponseDtoAssembler restauranteResponseDtoAssembler;
	
	@Autowired
	private RestauranteRequestDtoDesassembler restauranteRequestDtoDesassembler;
	
//	@GetMapping(consumes = MediaType.ALL_VALUE)
//	@ResponseStatus(HttpStatus.OK)
//	public List<RestauranteResponseDTO> listar(){
//		return restauranteResponseDtoAssembler.toDtoList(service.listar());
//	}
	
	@JsonView(RestauranteView.Resumo.class)
	@GetMapping(consumes = MediaType.ALL_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<RestauranteResponseDTO> listar(){
		return restauranteResponseDtoAssembler.toDtoList(service.listar());
	}
	
	@JsonView(RestauranteView.ApenasNome.class)
	@GetMapping(params = "projecao=apenas-nome", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<RestauranteResponseDTO> listarApenasNome(){
		return restauranteResponseDtoAssembler.toDtoList(service.listar());
	}
	
//	@JsonView(RestauranteView.Resumo.class)
//	@GetMapping(params = "projecao=resumo", consumes = MediaType.ALL_VALUE)
//	@ResponseStatus(HttpStatus.OK)
//	public List<RestauranteResponseDTO> listarResumido(){
//		return restauranteResponseDtoAssembler.toDtoList(service.listar());
//	}
	
//	@GetMapping(consumes = MediaType.ALL_VALUE)
//	@ResponseStatus(HttpStatus.OK)
//	public MappingJacksonValue listar(@RequestParam(required = false) String projecao ){
//		List<RestauranteResponseDTO> restaurantes = restauranteResponseDtoAssembler.toDtoList(service.listar());
//		MappingJacksonValue restaurantesWrapper = new MappingJacksonValue(restaurantes);
//		restaurantesWrapper.setSerializationView(RestauranteView.Resumo.class);
//		
//		if("apenas-nome".equals(projecao)) {
//			restaurantesWrapper.setSerializationView(RestauranteView.ApenasNome.class);
//		} else if ("completo".equals(projecao)) {
//			restaurantesWrapper.setSerializationView(null);
//		}
//		
//		return restaurantesWrapper;
//	}
	
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
		service.ativar(id);
	}
	
	@DeleteMapping(value = "{id}/ativo", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long id) {
		service.inativar(id);
	}
	
	@PutMapping(value = "ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> id) {
		service.ativar(id);
	}
	
	@DeleteMapping(value = "ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarMultiplos(@RequestBody List<Long> id) {
		service.inativar(id);
	}
	
	@PutMapping(value = "{id}/abertura", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abrir(@PathVariable Long id) {
		service.abrir(id);
	}
	
	@PutMapping(value = "{id}/fechamento", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fechar(@PathVariable Long id) {
		service.fechar(id);
	}
}
