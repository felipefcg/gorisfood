package br.com.felipe.gorisfood.infrastructure.service;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import br.com.felipe.gorisfood.domain.filter.VendaDiariaFilter;
import br.com.felipe.gorisfood.domain.model.Pedido;
import br.com.felipe.gorisfood.domain.model.projection.VendaDiaria;
import br.com.felipe.gorisfood.domain.service.VendaQueryService;

@Service
public class VendaQueryServiceImpl implements VendaQueryService {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<VendaDiaria> consultaVendasDiaria(VendaDiariaFilter filtro) {
		
//		var criteriaBuilder = em.getCriteriaBuilder();
//		var criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
//		var root = criteriaQuery.from(Pedido.class);
//		
//		criteriaBuilder.count(root.get("restaurante"));
////		criteriaBuilder.
//		criteriaQuery.groupBy(root.get("dataCriacao").as(LocalDate.class));
//		
//		
//		em.createQuery(criteriaQuery).getResultList()
//			.stream()
//			.forEach(v -> System.out.println(v));
		
		return null;
	}

}
