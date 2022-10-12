package br.com.felipe.gorisfood.infrasctructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		return em.find(Cidade.class, id);
	}

	@Override
	@Transactional
	public Cidade salvar(Cidade cidade) {
		return em.merge(cidade);
	}

	@Override
	@Transactional
	public void remover(Cidade cidade) {
		em.remove(cidade);

	}

}
