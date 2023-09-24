package br.com.felipe.gorisfood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.felipe.gorisfood.api.v1.controller.EstatisticasController.EstatisticaResponseDTO;
import br.com.felipe.gorisfood.domain.filter.VendaDiariaFilter;
import br.com.felipe.gorisfood.domain.model.projection.VendaDiaria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Estatísticas")
@SecurityRequirement(name = "security_auth")public interface EstatisticasControllerOpenApi {
	
	@Operation(hidden = true)
	public EstatisticaResponseDTO listaEndpointsEstatisticas();
	
	@Operation(summary = "Consulta estatísticas de vendas diárias",
			parameters = {
					@Parameter(
							in = ParameterIn.QUERY, name = "restauranteId", description = "ID do restaurante",
							schema = @Schema(type = "integer"), example = "1"),
					@Parameter(
							in = ParameterIn.QUERY, name = "dataCriacaoInicio", description = "Data/Hora inicial da criação do pedido",
							schema = @Schema(type = "string", format = "date-time"), example = "2019-12-01T00:00:00Z\""),
					@Parameter(
							in = ParameterIn.QUERY, name = "dataCriacaoFim", description = "Data/Hora final da criação do pedido",
							schema = @Schema(type = "string", format = "date-time"), example = "2019-12-02T23:59:59Z")
			},
			responses = {
					@ApiResponse(responseCode = "200",
							content = {
									@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = VendaDiaria.class))),
									@Content(mediaType = MediaType.APPLICATION_PDF_VALUE, schema = @Schema(type = "string", format = "binary"))
							})
			})
	public List<VendaDiaria> consultarVendasDiarias(@Parameter(hidden = true) VendaDiariaFilter filtro, 
			@Parameter(description = "Deslocamento de horário a ser considerado na consumta em relação ao UTC") String timeOffset);
	
	@Operation(hidden = true)
	public ResponseEntity<byte[]> consultarVendasDiariasPdf( VendaDiariaFilter filtro, 
			String timeOffset);
}
