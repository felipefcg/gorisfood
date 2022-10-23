package br.com.felipe.gorisfood.domain.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		Cozinha cozinha = cozinhaRepository.buscar(restaurante.getCozinha().getId());
		
		if(cozinha == null) {
			throw new EntidadeRelacionamentoNaoEncontradaException(
					String.format("Não existe um cadastro de cozinha com o código %d.", restaurante.getCozinha().getId()));
		}
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.salvar(restaurante);
	}

	public Restaurante alterar(Long id, Restaurante restaurante) {
		Restaurante restauranteSalvo = this.buscar(id);
		
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinhaSalva = cozinhaRepository.buscar(cozinhaId);
		
		if(cozinhaSalva == null) {
			throw new EntidadeRelacionamentoNaoEncontradaException(
					String.format("Cozinha com ID %d não encontrado.", cozinhaId));
		}
		
		restaurante.setCozinha(cozinhaSalva);
		BeanUtils.copyProperties(restaurante, restauranteSalvo, "id");
		
		return restauranteRepository.salvar(restauranteSalvo);
	}

	public Restaurante alterarParcialmente(Long id, Map<String, Object> campos) {
		
		Restaurante restauranteAtual = buscar(id);
		merge(campos, restauranteAtual);
		
		return restauranteRepository.salvar(restauranteAtual);
	}
	
	private void merge (Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
		camposOrigem.forEach((nomeCampo, valorCampo) -> {
			try {

				Field field = restauranteDestino.getClass().getDeclaredField(nomeCampo);
				field.setAccessible(true);
				
				if(field.getType().equals(BigDecimal.class)) {
					field.set(restauranteDestino, new BigDecimal((double) valorCampo, MathContext.DECIMAL32).setScale(2));
				} else {
					field.set(restauranteDestino, valorCampo);
				}
				
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				throw new EntidadeRelacionamentoNaoEncontradaException("Falha na leitura dos campos!!");
			}
		});
	}
	

}
