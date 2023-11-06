package br.com.felipe.gorisfood.infrastructure.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.domain.model.FotoProduto;
import br.com.felipe.gorisfood.domain.repository.ProdutoQueryRepository;

@Repository
public class ProdutoRepositoryImpl implements ProdutoQueryRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public FotoProduto save(FotoProduto fotoProduto) {
		return em.merge(fotoProduto);
	}

	@Transactional
	@Override
	public void delete(FotoProduto fotoProduto) {
		em.remove(fotoProduto);
		
	}


}
