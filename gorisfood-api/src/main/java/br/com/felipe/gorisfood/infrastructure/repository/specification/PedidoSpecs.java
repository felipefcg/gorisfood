package br.com.felipe.gorisfood.infrastructure.repository.specification;

import java.util.ArrayList;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.felipe.gorisfood.domain.filter.PedidoFilter;
import br.com.felipe.gorisfood.domain.model.Pedido;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PedidoSpecs {

	public static Specification<Pedido> usandoFiltro(PedidoFilter filter) {
		
		return (root, query, builder) -> {
			var predicates = new ArrayList<Predicate>();
			
			if(Pedido.class.equals(query.getResultType())) {
				root.fetch("restaurante").fetch("cozinha");
				root.fetch("cliente");
				root.fetch("enderecoEntrega").fetch("cidade").fetch("estado");
			}
			
			if(filter.getRestauranteId() != null) {
				predicates.add(builder.equal(root.get(Pedido.Fields.restaurante).get("id"), filter.getRestauranteId()));
			}
			
			if(filter.getClienteId() != null) {
				predicates.add(builder.equal(root.get(Pedido.Fields.cliente).get("id"), filter.getClienteId()));
			}
			
			if(filter.getDataCriacaoInicio() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get(Pedido.Fields.dataCriacao), filter.getDataCriacaoInicio()));
			}
			
			if(filter.getDataCriacaoFim() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get(Pedido.Fields.dataCriacao), filter.getDataCriacaoFim()));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
