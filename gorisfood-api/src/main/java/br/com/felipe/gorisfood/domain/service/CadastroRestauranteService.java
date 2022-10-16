package br.com.felipe.gorisfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipe.gorisfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository repository;
	
	public List<Restaurante> listar() {
		return repository.listar();
	}
	
	public Restaurante buscar(Long id) {
		
		 Restaurante restaurante = repository.buscar(id);
		 
		 if(restaurante == null) {
			 throw new EntidadeNaoEncontradaException(
					 String.format("Restaurante não encontrado com o código %d.", id));
		 }
		 
		 return restaurante;
	}

}
