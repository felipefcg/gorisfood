package br.com.felipe.gorisfood.infrasctructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.domain.model.Estado;
import br.com.felipe.gorisfood.domain.repository.EstadoRepository;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Estado> listar() {
		return em.createQuery("from Estado", Estado.class).getResultList();
	}

	@Override
	public Estado buscar(Long id) {
		Estado estado = em.find(Estado.class, id);
		
		if(estado==null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return estado;
	}

	@Override
	@Transactional
	public Estado salvar(Estado estado) {
		return em.merge(estado);
	}

	@Override
	@Transactional
	public void remover(Long id) {
		Estado estado = buscar(id);
		em.remove(estado);
	}

}
