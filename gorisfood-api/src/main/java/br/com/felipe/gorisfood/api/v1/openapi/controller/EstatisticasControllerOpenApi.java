package br.com.felipe.gorisfood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.v1.controller.EstatisticasController.EstatisticaResponseDTO;
import br.com.felipe.gorisfood.domain.filter.VendaDiariaFilter;
import br.com.felipe.gorisfood.domain.model.projection.VendaDiaria;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Estatísticas")
@SecurityRequirement(name = "security_auth")public interface EstatisticasControllerOpenApi {
	public EstatisticaResponseDTO listaEndpointsEstatisticas();
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, 
			String timeOffset);
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro, String timeOffset);
}
