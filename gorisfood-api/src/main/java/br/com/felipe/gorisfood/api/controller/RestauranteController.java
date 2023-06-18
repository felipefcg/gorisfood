package br.com.felipe.gorisfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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

import br.com.felipe.gorisfood.api.assembler.RestauranteApenasNomeResponseDtoAssembler;
import br.com.felipe.gorisfood.api.assembler.RestauranteBasicoResponseDTOAssembler;
import br.com.felipe.gorisfood.api.assembler.RestauranteRequestDtoDesassembler;
import br.com.felipe.gorisfood.api.assembler.RestauranteResponseDtoAssembler;
import br.com.felipe.gorisfood.api.model.request.RestauranteRequestDTO;
import br.com.felipe.gorisfood.api.model.response.RestauranteApenasNomeResponseDTO;
import br.com.felipe.gorisfood.api.model.response.RestauranteBasicoResponseDTO;
import br.com.felipe.gorisfood.api.model.response.RestauranteResponseDTO;
import br.com.felipe.gorisfood.api.openapi.controller.RestauranteControllerOpenApi;
import br.com.felipe.gorisfood.domain.exception.CidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.CozinhaNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.EntidadeRelacionamentoNaoEncontradaException;
import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController implements RestauranteControllerOpenApi {
	
	@Autowired
	private CadastroRestauranteService service;

	@Autowired
	private RestauranteResponseDtoAssembler restauranteResponseDtoAssembler;
	
	@Autowired
	private RestauranteBasicoResponseDTOAssembler restauranteBasicoResponseDTOAssembler; 
	
	@Autowired
	private RestauranteApenasNomeResponseDtoAssembler restauranteApenasNomeResponseDtoAssembler;
	@Autowired
	private RestauranteRequestDtoDesassembler restauranteRequestDtoDesassembler;

//	@JsonView(RestauranteView.Resumo.class)
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CollectionModel<RestauranteBasicoResponseDTO> listar(){
		return restauranteBasicoResponseDTOAssembler.toCollectionModel(service.listar());
	}
	
//	@JsonView(RestauranteView.ApenasNome.class)
	@GetMapping(params = "projecao=apenas-nome")
	@ResponseStatus(HttpStatus.OK)
	public CollectionModel<RestauranteApenasNomeResponseDTO> listarApenasNome(){
		return restauranteApenasNomeResponseDtoAssembler.toCollectionModel(service.listar());
	}
	
	@GetMapping(value =  "{id}", consumes = MediaType.ALL_VALUE)
	public RestauranteResponseDTO buscar(@PathVariable Long id){
		return restauranteResponseDtoAssembler.toModel(service.buscar(id));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteResponseDTO criar(@RequestBody @Valid RestauranteRequestDTO restauranteDTO) {
		try {	
			Restaurante restaurante = restauranteRequestDtoDesassembler.toDomain(restauranteDTO);
			return restauranteResponseDtoAssembler.toModel(service.criar(restaurante));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new EntidadeRelacionamentoNaoEncontradaException(e.getMessage());
		}
	}

	@PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public RestauranteResponseDTO alterar(@PathVariable Long id, @RequestBody @Valid RestauranteRequestDTO restauranteDTO) {
		try {
			Restaurante restaurante = service.buscar(id);
			restauranteRequestDtoDesassembler.copyToDomain(restauranteDTO, restaurante);
			return restauranteResponseDtoAssembler.toModel(service.alterar(restaurante));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new EntidadeRelacionamentoNaoEncontradaException(e.getMessage());
		}
	}

//	@GetMapping(value = "por-nome")
//	@ResponseStatus(value = HttpStatus.OK)
//	public CollectionModel<RestauranteResponseDTO> buscarPorNomeECozinha(String nome, Long cozinhaId) {
//		return restauranteResponseDtoAssembler.toCollectionModel(service.buscarPorNomeECozinha(nome, cozinhaId));
//	}
	
//	@GetMapping(value = "por-nome-e-taxa")
//	@ResponseStatus(value = HttpStatus.OK)
//	public List<RestauranteResponseDTO> buscarPorNomeETaxa(String nome, BigDecimal taxaInicio, BigDecimal taxaFim) {
//		return restauranteResponseDtoAssembler.toModelList(service.buscarPorNomeETaxa(nome, taxaInicio, taxaFim));
//	}
//	
//	@GetMapping(value = "por-nome-cozinha-e-taxa")
//	@ResponseStatus(value = HttpStatus.OK)
//	public List<RestauranteResponseDTO> buscarPorCozinhaETaxa(String nomeCozinha, BigDecimal taxaInicial, BigDecimal taxaFinal) {
//		return restauranteResponseDtoAssembler.toModelList(service.buscarPorCozinhaETaxa(nomeCozinha, taxaInicial, taxaFinal));
//	}
//	
//	@GetMapping(value = "por-frete-gratis-e-nome")
//	@ResponseStatus(value = HttpStatus.OK)
//	public List<RestauranteResponseDTO> buscarPorFreteGratisENome(String nome) {
//		return restauranteResponseDtoAssembler.toModelList(service.buscarPorFreteGratisENome(nome));
//	}
//	
//	@GetMapping(value = "primeiro")
//	@ResponseStatus(value = HttpStatus.OK)
//	public Optional<RestauranteResponseDTO> buscarPrimeiro() {
//		return restauranteResponseDtoAssembler.toModel(service.buscarPrimeiro());
//	}
	
	@PutMapping(value = "{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> ativar(@PathVariable Long id) {
		service.ativar(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
		service.inativar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> ativarMultiplos(@RequestBody List<Long> id) {
		service.ativar(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> inativarMultiplos(@RequestBody List<Long> id) {
		service.inativar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "{id}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> abrir(@PathVariable Long id) {
		service.abrir(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "{id}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> fechar(@PathVariable Long id) {
		service.fechar(id);
		return ResponseEntity.noContent().build();
	}
}
