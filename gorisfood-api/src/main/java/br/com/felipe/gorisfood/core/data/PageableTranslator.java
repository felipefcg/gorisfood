package br.com.felipe.gorisfood.core.data;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageableTranslator {

	public static Pageable translate(Pageable apiPageable, Map<String, String> fieldsMap) {
		var orders = apiPageable.getSort()
				.stream()
				.filter(order -> fieldsMap.containsKey(order.getProperty()))
				.map(order -> new  Sort.Order(order.getDirection(), fieldsMap.get(order.getProperty())))
				.toList();
				
		return PageRequest.of(apiPageable.getPageNumber(), apiPageable.getPageSize(), Sort.by(orders));
	}

}
