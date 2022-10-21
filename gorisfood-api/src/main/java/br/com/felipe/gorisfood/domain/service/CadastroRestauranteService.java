package br.com.felipe.gorisfood.domain.service;

import java.util.List;

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

}
