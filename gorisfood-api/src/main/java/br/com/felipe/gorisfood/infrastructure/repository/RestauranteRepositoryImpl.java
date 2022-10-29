package br.com.felipe.gorisfood.infrastructure.repository;

import static br.com.felipe.gorisfood.infrastructure.repository.specification.RestauranteSpecs.comFreteGratis;
import static br.com.felipe.gorisfood.infrastructure.repository.specification.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.repository.RestauranteRepository;
import br.com.felipe.gorisfood.domain.repository.RestauranteRepositoryCustom;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	@Lazy
	private RestauranteRepository repository;
	
	@Override
	public List<Restaurante> consultarPorCozinhaETaxa(String nomeCozinha, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		
		var parametros = new ArrayList<Predicate>();
		var builder = em.getCriteriaBuilder();
		var criteria = builder.createQuery(Restaurante.class);
		var root = criteria.from(Restaurante.class);
		
		if(StringUtils.hasLength(nomeCozinha)) {
			Join<Object, Object> join = root.join("cozinha");
			parametros.add(builder.like(join.get("nome"), "%" + nomeCozinha + "%"));
		}
			
		if(taxaInicial != null) {
			parametros.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaInicial));
		}
		
		if(taxaFinal != null) {
			parametros.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFinal));
		}
		
		criteria.where(  parametros.toArray(new Predicate[parametros.size()]));
		return em.createQuery(criteria).getResultList();
		
	}

	@Override
	public List<Restaurante> consultarFreteGratis(String nome) {
		return repository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
	}

}
