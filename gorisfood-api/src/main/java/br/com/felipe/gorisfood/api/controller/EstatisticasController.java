package br.com.felipe.gorisfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.domain.filter.VendaDiariaFilter;
import br.com.felipe.gorisfood.domain.model.projection.VendaDiaria;
import br.com.felipe.gorisfood.domain.service.VendaQueryService;
import br.com.felipe.gorisfood.domain.service.VendaReportService;

@RestController
@RequestMapping(value = "estatisticas", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstatisticasController {

	@Autowired
	private VendaQueryService vendaQueryService;
	
	@Autowired
	private VendaReportService vendaReportService;
	
	@GetMapping("vendas-diarias")
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, @RequestParam(defaultValue = "+00:00") String timeOffset) {
		System.out.println("JSON");
		return vendaQueryService.consultaVendasDiaria(filtro, timeOffset);
	}
	
	@GetMapping(path = "vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE )
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro, @RequestParam(defaultValue = "+00:00") String timeOffset) {
		var report = vendaReportService.consultaVendasDiaria(filtro, timeOffset);
		System.out.println("PDF");
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diaria.pdf")
				.body(report);
	}
}
