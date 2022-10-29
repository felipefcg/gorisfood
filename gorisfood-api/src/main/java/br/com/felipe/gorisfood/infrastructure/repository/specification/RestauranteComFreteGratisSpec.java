package br.com.felipe.gorisfood.infrastructure.repository.specification;

import java.math.BigDecimal;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.felipe.gorisfood.domain.model.Restaurante;

public class RestauranteComFreteGratisSpec implements Specification<Restaurante> {

	private static final long serialVersionUID = -6936585162245846715L;

	@Override
	public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}

}
