package br.com.felipe.gorisfood.infrastructure.service.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.com.felipe.gorisfood.domain.filter.VendaDiariaFilter;
import br.com.felipe.gorisfood.domain.model.Pedido;
import br.com.felipe.gorisfood.domain.model.enums.StatusPedido;
import br.com.felipe.gorisfood.domain.model.projection.VendaDiaria;
import br.com.felipe.gorisfood.domain.service.VendaQueryService;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<VendaDiaria> consultaVendasDiaria(VendaDiariaFilter filtro, String timeOffset) {
		
		var criteriaBuilder = em.getCriteriaBuilder();
		var criteriaQuery = criteriaBuilder.createQuery(VendaDiaria.class);
		var root = criteriaQuery.from(Pedido.class);
		
		var functionDataCriacao = criarFuncaoDataCriacao(criteriaBuilder, root, timeOffset);
		
		var selection = criteriaBuilder.construct(VendaDiaria.class, 
				functionDataCriacao, criteriaBuilder.count(root), criteriaBuilder.sum(root.get("valorTotal")));
		
		criteriaQuery.select(selection);
		criteriaQuery.where(criarClausulasWhere(criteriaBuilder, root, filtro));
		criteriaQuery.groupBy(functionDataCriacao);
		
		return em.createQuery(criteriaQuery).getResultList();
	}


	private Expression<Date> criarFuncaoDataCriacao(CriteriaBuilder builder, Root<Pedido> root, String timeOffset) {
		//convert_tz: função do MySQL para alterar o TimeZone da data 
		var functionConvertTz = builder.function("convert_tz", Date.class, root.get("dataCriacao"), builder.literal("+00:00"), builder.literal(timeOffset));
		
		//date: é uma função do MySql, como o TO_DATE é do Oracle
		var functionDate = builder.function("date", Date.class, functionConvertTz);
		return functionDate;
	}

	
	private Predicate[] criarClausulasWhere(CriteriaBuilder builder, Root<Pedido> root, VendaDiariaFilter filtro) {
		var predicates = new ArrayList<Predicate>();
		predicates.add(root.get("status").in(Arrays.asList(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE)));
		
		if(filtro.getRestauranteId() !=  null) {
			predicates.add(builder.equal(root.get(Pedido.Fields.restaurante).get("id"), filtro.getRestauranteId()));
		}
		
		if(filtro.getDataCriacaoInicio() !=  null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Pedido.Fields.dataCriacao), 
														filtro.getDataCriacaoInicio()));
		}

		if(filtro.getDataCriacaoFim() !=  null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Pedido.Fields.dataCriacao), 
													filtro.getDataCriacaoFim()));
		}
		
		return predicates.toArray(new Predicate[0]);
	}
}
