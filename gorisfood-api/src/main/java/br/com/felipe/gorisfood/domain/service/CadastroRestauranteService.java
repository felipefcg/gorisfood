package br.com.felipe.gorisfood.domain.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
		return restauranteRepository.listar();
	}
	
	public Restaurante buscar(Long id) {
		
		 Restaurante restaurante = restauranteRepository.buscar(id);
		 
		 if(restaurante == null) {
			 throw new EntidadeNaoEncontradaException(
					 String.format("Restaurante não encontrado com o código %d.", id));
		 }
		 
		 return restaurante;
	}

	public Restaurante criar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
							.orElseThrow(() -> new EntidadeRelacionamentoNaoEncontradaException(
									String.format("Não existe um cadastro de cozinha com o código %d.", cozinhaId)));
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.salvar(restaurante);
	}

	public Restaurante alterar(Long id, Restaurante restaurante) {
		Restaurante restauranteSalvo = this.buscar(id);
		buscaCozinha(restaurante);
		BeanUtils.copyProperties(restaurante, restauranteSalvo, "id");
		
		return restauranteRepository.salvar(restauranteSalvo);
	}

	public Restaurante alterarParcialmente(Long id, Map<String, Object> campos) {
		
		Restaurante restauranteDestino = buscar(id);
		merge(campos, restauranteDestino);
		buscaCozinha(restauranteDestino);
		
		return restauranteRepository.salvar(restauranteDestino);
	}
	
	private void buscaCozinha(Restaurante restaurante) {
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
	

}
