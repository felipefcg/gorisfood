package br.com.felipe.gorisfood.domain.model.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class VendaDiaria {
	
	private LocalDate data;
	private Integer totalVendas;
	private BigDecimal totalFaturado;
}
