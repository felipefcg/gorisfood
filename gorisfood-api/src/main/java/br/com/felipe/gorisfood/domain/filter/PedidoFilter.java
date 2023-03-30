package br.com.felipe.gorisfood.domain.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoFilter {
	
	@ApiModelProperty(example = "1")
	private Long restauranteId;
	
	@ApiModelProperty(example = "1")
	private Long clienteId;
	
	@ApiModelProperty(example = "2019-10-31T12:00:00Z")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoInicio;
	
	@ApiModelProperty(example = "2019-10-31T12:00:00Z")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoFim;
}
