package br.com.felipe.gorisfood.domain.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.felipe.gorisfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.EntidadeRelacionamentoNaoEncontradaException;
import br.com.felipe.gorisfood.domain.model.Cozinha;
import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.repository.CozinhaRepository;
import br.com.felipe.gorisfood.domain.repository.RestauranteRepository;


@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}
	
	public Restaurante buscar(Long id) {
		
		 return restauranteRepository.findById(id)
				 .orElseThrow(() -> new EntidadeNaoEncontradaException(
						 String.format("Restaurante não encontrado com o código %d.", id)));
	}

	public Restaurante criar(Restaurante restaurante) {
		colocarCozinhaNoRestaurante(restaurante);
		return restauranteRepository.save(restaurante);
	}

	public Restaurante alterar(Long id, Restaurante restaurante) {
		Restaurante restauranteSalvo = buscar(id);
		colocarCozinhaNoRestaurante(restaurante);
		BeanUtils.copyProperties(restaurante, restauranteSalvo, 
				"id", "formasPagamento", "endereco", "dataCadastro", "produtos");
		
		return restauranteRepository.save(restauranteSalvo);
	}

	public Restaurante alterarParcialmente(Long id, Map<String, Object> campos) {
		
		Restaurante restauranteDestino = buscar(id);
		merge(campos, restauranteDestino);
		colocarCozinhaNoRestaurante(restauranteDestino);
		
		return restauranteRepository.save(restauranteDestino);
	}
	
	private void colocarCozinhaNoRestaurante(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinhaSalva = cozinhaRepository.findById(cozinhaId)
								.orElseThrow(() -> new EntidadeRelacionamentoNaoEncontradaException(
												String.format("Cozinha com ID %d não encontrado.", cozinhaId)));
		
		restaurante.setCozinha(cozinhaSalva);
	}
	
	private void merge (Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
		ObjectMapper mapper = new ObjectMapper();
		Restaurante restauranteOrigem = mapper.convertValue(dadosOrigem, Restaurante.class);
		
		dadosOrigem.forEach((nomeCampo, valorCampo) -> {

				Field field = ReflectionUtils.findField(Restaurante.class, nomeCampo);
				field.setAccessible(true);
				
				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
				
				ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}

	public List<Restaurante> buscarPorNomeECozinha(String nome, Long cozinhaId) {
		return restauranteRepository.consultaPorNome(nome, cozinhaId);
	}
	
	public List<Restaurante> buscarPorNomeETaxa(String nome, BigDecimal taxaInicio, BigDecimal taxaFinal) {
		return restauranteRepository.consultaPorNomeETaxaFrete(nome, taxaInicio, taxaFinal);
	}
	
	public List<Restaurante> buscarPorCozinhaETaxa(String nome, BigDecimal taxaInicio, BigDecimal taxaFinal) {
		return restauranteRepository.consultarPorCozinhaETaxa(nome, taxaInicio, taxaFinal);
	}
	
	public List<Restaurante> buscarPorFreteGratisENome(String nome) {
		return restauranteRepository.consultarFreteGratis(nome);
	}

	public Optional<Restaurante> buscarPrimeiro() {
		return restauranteRepository.buscarPrimeiro();
	}
}
