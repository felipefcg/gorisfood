package br.com.felipe.gorisfood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.felipe.gorisfood.domain.exception.CozinhaNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.EntidadeEmUsoExcpetion;
import br.com.felipe.gorisfood.domain.model.Cozinha;
import br.com.felipe.gorisfood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida pois está em uso.";

	@Autowired
	private CozinhaRepository repository;
	
	public Cozinha salvar(Cozinha cozinha) {
		return repository.save(cozinha);
	}
	
	public List<Cozinha> listar(){
		return repository.findAll();
	}
	
	public Cozinha buscar(Long id) {
		
		return repository.findById(id).orElseThrow(() -> new CozinhaNaoEncontradaException(id));
		
	}
	
	public void remover(Long id) throws DataIntegrityViolationException {
		try {
			repository.deleteById(id);
		
		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExcpetion(
					String.format(MSG_COZINHA_EM_USO, id));
		}
	}

	public Optional<Cozinha> buscarPrimeiro() {
		return repository.buscarPrimeiro();
	}
}
