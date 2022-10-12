package br.com.felipe.gorisfood.infrasctructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.domain.model.Permissao;
import br.com.felipe.gorisfood.domain.repository.PermissaoRepository;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Permissao> listar() {
		return em.createQuery("from Permissao", Permissao.class).getResultList();
	}

	@Override
	public Permissao buscar(Long id) {
		return em.find(Permissao.class, id);
	}

	@Override
	@Transactional
	public Permissao salvar(Permissao permissao) {
		return em.merge(permissao);
	}

	@Override
	@Transactional
	public void remover(Permissao permissao) {
		em.remove(permissao);
	}

}
