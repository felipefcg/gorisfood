package br.com.felipe.gorisfood.domain.service;

import java.util.List;

import br.com.felipe.gorisfood.domain.filter.VendaDiariaFilter;
import br.com.felipe.gorisfood.domain.model.projection.VendaDiaria;

public interface VendaQueryService {

	List<VendaDiaria> consultaVendasDiaria(VendaDiariaFilter filtor);
}
