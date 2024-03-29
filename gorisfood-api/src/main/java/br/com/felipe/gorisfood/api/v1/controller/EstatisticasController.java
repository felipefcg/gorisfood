package br.com.felipe.gorisfood.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.openapi.controller.EstatisticasControllerOpenApi;
import br.com.felipe.gorisfood.core.security.CheckSecurity;
import br.com.felipe.gorisfood.domain.filter.VendaDiariaFilter;
import br.com.felipe.gorisfood.domain.model.projection.VendaDiaria;
import br.com.felipe.gorisfood.domain.service.VendaQueryService;
import br.com.felipe.gorisfood.domain.service.VendaReportService;

@RestController
@RequestMapping(value = "v1/estatisticas", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstatisticasController implements EstatisticasControllerOpenApi {

	@Autowired
	private VendaQueryService vendaQueryService;
	
	@Autowired
	private VendaReportService vendaReportService;
	
	@Autowired
	private GorisLinks gorisLinks;
	
	public class EstatisticaResponseDTO extends RepresentationModel<EstatisticaResponseDTO> {}
	
	@CheckSecurity.Estatisticas.PodeConsultar
	@GetMapping
	public EstatisticaResponseDTO listaEndpointsEstatisticas() {
		var dto = new EstatisticaResponseDTO();
		dto.add(gorisLinks.linkToEstatiticasVendasDiarias("vendas-diarias"));
		
		return dto;
	}
	
	@CheckSecurity.Estatisticas.PodeConsultar
	@GetMapping("vendas-diarias")
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, @RequestParam(defaultValue = "+00:00") String timeOffset) {
		System.out.println("JSON");
		return vendaQueryService.consultaVendasDiaria(filtro, timeOffset);
	}
	
	@CheckSecurity.Estatisticas.PodeConsultar
	@GetMapping(path = "vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE )
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro, @RequestParam(defaultValue = "+00:00") String timeOffset) {
		var report = vendaReportService.consultaVendasDiaria(filtro, timeOffset);
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diaria.pdf")
				.body(report);
	}
}
