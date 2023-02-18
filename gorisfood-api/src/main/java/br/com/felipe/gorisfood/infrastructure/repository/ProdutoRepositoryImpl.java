package br.com.felipe.gorisfood.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	public FotoProduto salvarFotoProduto(FotoProduto fotoProduto) {
		return em.merge(fotoProduto);
	}


}
