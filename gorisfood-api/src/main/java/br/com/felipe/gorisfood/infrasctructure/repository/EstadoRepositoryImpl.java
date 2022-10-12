package br.com.felipe.gorisfood.infrasctructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		return em.find(Estado.class, id);
	}

	@Override
	@Transactional
	public Estado salvar(Estado estado) {
		return em.merge(estado);
	}

	@Override
	@Transactional
	public void remover(Estado estado) {
		em.remove(estado);
	}

}
