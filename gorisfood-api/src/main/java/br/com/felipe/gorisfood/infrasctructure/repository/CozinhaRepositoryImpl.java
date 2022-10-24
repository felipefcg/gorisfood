package br.com.felipe.gorisfood.infrasctructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.domain.model.Cozinha;
import br.com.felipe.gorisfood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Cozinha> listar() {
		return em.createQuery("from Cozinha", Cozinha.class).getResultList();
	}
	
	@Override
	public Cozinha buscar(Long id) {
		Cozinha cozinha = em.find(Cozinha.class, id);
		
		if(cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return cozinha;
	}
	
	@Override
	@Transactional
	public Cozinha salvar (Cozinha cozinha) {
		return em.merge(cozinha);
	}

	@Override
	@Transactional
	public void remover(Long id) {
		Cozinha cozinha = this.buscar(id);
		
		if(cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		em.remove(cozinha);
	}
	

}
