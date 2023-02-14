package br.com.felipe.gorisfood.domain.service;

import br.com.felipe.gorisfood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {
	byte[] consultaVendasDiaria(VendaDiariaFilter filtro, String timeOffset);
}
