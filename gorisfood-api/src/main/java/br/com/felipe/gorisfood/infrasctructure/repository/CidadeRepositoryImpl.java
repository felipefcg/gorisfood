package br.com.felipe.gorisfood.infrasctructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.domain.model.Cidade;
import br.com.felipe.gorisfood.domain.repository.CidadeRepository;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Cidade> listar() {
		return em.createQuery("from Cidade", Cidade.class).getResultList();
	}

	@Override
	public Cidade buscar(Long id) {
		Cidade cidade = em.find(Cidade.class, id);
		
		if(cidade == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return cidade;
	}

	@Override
	@Transactional
	public Cidade salvar(Cidade cidade) {
		return em.merge(cidade);
	}

	@Override
	@Transactional
	public void remover(Long id) {
		Cidade cidade = buscar(id);
		em.remove(cidade);
	}

}
