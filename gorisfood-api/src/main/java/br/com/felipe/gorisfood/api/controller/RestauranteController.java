package br.com.felipe.gorisfood.api.controller;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import br.com.felipe.gorisfood.api.model.input.RestauranteInputDTO;
import br.com.felipe.gorisfood.api.model.output.CozinhaOutputDTO;
import br.com.felipe.gorisfood.api.model.output.RestauranteOutputDTO;
import br.com.felipe.gorisfood.core.validation.ValidacaoUtils;
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

	@GetMapping(consumes = MediaType.ALL_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<RestauranteOutputDTO> listar(){
		return toDtoList(service.listar());
	}
	
	@GetMapping(value =  "{id}", consumes = MediaType.ALL_VALUE)
	public RestauranteOutputDTO buscar(@PathVariable Long id){
		return toDTO(service.buscar(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteOutputDTO criar(@RequestBody @Valid RestauranteInputDTO restauranteDTO) {
		try {	
			Restaurante restaurante = toDomain(restauranteDTO);
			return toDTO(service.criar(restaurante));
		} catch (CozinhaNaoEncontradaException e) {
			throw new EntidadeRelacionamentoNaoEncontradaException(e.getMessage());
		}
	}

	private Restaurante toDomain(RestauranteInputDTO restauranteDTO) {
		Cozinha cozinha = Cozinha.builder()
									.id(restauranteDTO.cozinha().id())
									.build();
		return Restaurante.builder()
					.nome(restauranteDTO.nome())
					.taxaFrete(restauranteDTO.taxaFrete())
					.cozinha(cozinha)
					.build();
	}

	@PutMapping("{id}")
	public RestauranteOutputDTO alterar(@PathVariable Long id, @RequestBody @Valid Restaurante restaurante) {
		try {
			return toDTO(service.alterar(id, restaurante));
		} catch (CozinhaNaoEncontradaException e) {
			throw new EntidadeRelacionamentoNaoEncontradaException(e.getMessage());
		}
	}
	
	@PatchMapping("{id}")
	public RestauranteOutputDTO alterarParcialmente(@PathVariable Long id, @RequestBody @Valid Map<String, Object> campos,
			HttpServletRequest request) {
		
		Restaurante restauranteDestino = service.buscar(id);
		merge(campos, restauranteDestino, request);
		ValidacaoUtils.validate(restauranteDestino, "restaurante");
		
		try {
			return toDTO(service.alterarParcialmente(restauranteDestino));
		} catch (CozinhaNaoEncontradaException e) {
			throw new EntidadeRelacionamentoNaoEncontradaException(e.getMessage());
		}
	}

	@GetMapping(value = "por-nome", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<RestauranteOutputDTO> buscarPorNomeECozinha(String nome, Long cozinhaId) {
		return toDtoList(service.buscarPorNomeECozinha(nome, cozinhaId));
	}
	
	@GetMapping(value = "por-nome-e-taxa", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<RestauranteOutputDTO> buscarPorNomeETaxa(String nome, BigDecimal taxaInicio, BigDecimal taxaFim) {
		return toDtoList(service.buscarPorNomeETaxa(nome, taxaInicio, taxaFim));
	}
	
	@GetMapping(value = "por-nome-cozinha-e-taxa", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<RestauranteOutputDTO> buscarPorCozinhaETaxa(String nomeCozinha, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return toDtoList(service.buscarPorCozinhaETaxa(nomeCozinha, taxaInicial, taxaFinal));
	}
	
	@GetMapping(value = "por-frete-gratis-e-nome", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<RestauranteOutputDTO> buscarPorFreteGratisENome(String nome) {
		return toDtoList(service.buscarPorFreteGratisENome(nome));
	}
	
	@GetMapping(value = "primeiro", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public Optional<RestauranteOutputDTO> buscarPrimeiro() {
		return toDTO(service.buscarPrimeiro());
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
	
	private RestauranteOutputDTO toDTO(Restaurante restaurante) {
		CozinhaOutputDTO cozinhaOutputDTO = CozinhaOutputDTO.builder()
												.id(restaurante.getCozinha().getId())
												.nome(restaurante.getCozinha().getNome())
												.build();
		
		return RestauranteOutputDTO.builder()
				.id(restaurante.getId())
				.nome(restaurante.getNome())
				.taxaFrete(restaurante.getTaxaFrete())
				.cozinha(cozinhaOutputDTO)
				.build();
	}
	
	private List<RestauranteOutputDTO> toDtoList(List<Restaurante> restaurantes) {
		return restaurantes.stream()
					.map(r -> toDTO(r))
					.toList();
	}
	
	private Optional<RestauranteOutputDTO> toDTO(Optional<Restaurante> restaurante) {
		if(restaurante.isEmpty()) {
			return Optional.empty();
		}
		
		return Optional.of(toDTO(restaurante.get()));
		
	}
}
