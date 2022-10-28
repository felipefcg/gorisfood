package br.com.felipe.gorisfood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.felipe.gorisfood.domain.model.Restaurante;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Restaurante> consultarPorCozinhaETaxa(String nomeCozinha, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		var jpql = "from Restaurante where cozinha.nome like :nome and taxaFrete between :taxaInicial and :taxaFinal";
		return em.createQuery(jpql, Restaurante.class)
				.setParameter("nome", "%" + nomeCozinha + "%")
				.setParameter("taxaInicial", taxaInicial)
				.setParameter("taxaFinal", taxaFinal)
				.getResultList();
	}
}
