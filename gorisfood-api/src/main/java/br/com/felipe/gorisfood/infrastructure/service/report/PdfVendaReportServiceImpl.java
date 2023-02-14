package br.com.felipe.gorisfood.infrastructure.service.report;

import org.springframework.stereotype.Service;

import br.com.felipe.gorisfood.domain.filter.VendaDiariaFilter;
import br.com.felipe.gorisfood.domain.service.VendaReportService;

@Service
public class PdfVendaReportServiceImpl implements VendaReportService {

	@Override
	public byte[] consultaVendasDiaria(VendaDiariaFilter filtro, String timeOffset) {
		return null;
	}

}
