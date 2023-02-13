package br.com.felipe.gorisfood.infrastructure.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.com.felipe.gorisfood.domain.filter.VendaDiariaFilter;
import br.com.felipe.gorisfood.domain.model.Pedido;
import br.com.felipe.gorisfood.domain.model.projection.VendaDiaria;
import br.com.felipe.gorisfood.domain.service.VendaQueryService;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<VendaDiaria> consultaVendasDiaria(VendaDiariaFilter filtro) {
		
		var criteriaBuilder = em.getCriteriaBuilder();
		var criteriaQuery = criteriaBuilder.createQuery(VendaDiaria.class);
		var root = criteriaQuery.from(Pedido.class);
		
		//date: é uma funcção do MySql, como o TO_DATE é do Oracle
		var functionDateDataCriacao = criteriaBuilder.function("date", Date.class, root.get("dataCriacao"))
													 .as(LocalDate.class);
		
		var selection = criteriaBuilder.construct(VendaDiaria.class, 
				functionDateDataCriacao, criteriaBuilder.count(root), criteriaBuilder.sum(root.get("valorTotal")));
		
		criteriaQuery.select(selection);
		criteriaQuery.where(criarClausulasWhere(criteriaBuilder, root, filtro));
		criteriaQuery.groupBy(functionDateDataCriacao);
		
		return em.createQuery(criteriaQuery).getResultList();
	}

	private Predicate[] criarClausulasWhere(CriteriaBuilder builder, Root<Pedido> root, VendaDiariaFilter filtro) {
		var predicates = new ArrayList<Predicate>();
		
		if(filtro.getRestauranteId() !=  null) {
			predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
		}
		
		if(filtro.getDataCriacaoInicio() !=  null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
		}

		if(filtro.getDataCriacaoFim() !=  null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
		}
		
		return predicates.toArray(new Predicate[0]);
	}
}
