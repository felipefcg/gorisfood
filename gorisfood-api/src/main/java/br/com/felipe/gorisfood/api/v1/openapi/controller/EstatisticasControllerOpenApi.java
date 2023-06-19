package br.com.felipe.gorisfood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.v1.controller.EstatisticasController.EstatisticaResponseDTO;
import br.com.felipe.gorisfood.domain.filter.VendaDiariaFilter;
import br.com.felipe.gorisfood.domain.model.projection.VendaDiaria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Estatísticas")
public interface EstatisticasControllerOpenApi {
	
	@ApiOperation(value = "Estatísticas", hidden = true)
	public EstatisticaResponseDTO listaEndpointsEstatisticas();
	
	@ApiOperation(value = "Consulta estatísticas de vendas diárias", hidden = false)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "restauranteId", value = "ID do restaurante", example = "1", dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "dataCriacaoInicio", value = "Data/Hora inicial da criação do pedido", example = "2019-12-01T00:00:00Z", 
						  dataType = "date-time"),
		@ApiImplicitParam(name = "dataCriacaoFim", value = "Data/Hora final da criação do pedido", example = "2019-12-02T23:59:59Z", 
						  dataType = "date-time")
	})
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, 
			@ApiParam(value =  "Deslocamento de horário a ser considerado na consumta em relação ao UTC", defaultValue = "+00:00") String timeOffset);
	
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro, String timeOffset);

}
