package br.com.felipe.gorisfood.infrastructure.repository.specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.felipe.gorisfood.domain.model.Pedido;
import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.repository.filter.PedidoFilter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PedidoSpecs {

	public static Specification<Pedido> usandoFiltro(PedidoFilter filter) {
		
		return (root, query, builder) -> {
			var predicates = new ArrayList<Predicate>();
			
			if(filter.getRestauranteId() != null) {
				predicates.add(builder.equal(root.get("restaurante"), filter.getRestauranteId()));
			}
			
			if(filter.getClienteId() != null) {
				predicates.add(builder.equal(root.get("cliente"), filter.getClienteId()));
			}
			
			if(filter.getDataCriacaoInico() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacaoInico()));
			}
			
			if(filter.getDataCriacaoFim() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacaoFim()));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
