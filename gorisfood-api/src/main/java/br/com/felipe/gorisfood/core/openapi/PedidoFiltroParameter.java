package br.com.felipe.gorisfood.core.openapi;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.models.media.DateSchema;

@Retention(RUNTIME)
@Target({ METHOD, ANNOTATION_TYPE })
@Parameter(
		in = ParameterIn.QUERY,
		name = "restauranteId",
		description = "ID do restaurante para filtro da pesquisa",
		schema = @Schema(type = "integer", defaultValue = "1")
)
@Parameter(
		in = ParameterIn.QUERY,
		name = "clienteId",
		description = "ID do cliente para filtro da pesquisa",
		schema = @Schema(type = "integer", defaultValue = "1")
)
@Parameter(
		in = ParameterIn.QUERY,
		name = "dataCriacaoInicio",
		description = "Data/hora de criação inicial para filtro da pesquisa",
		schema = @Schema(type = "string", format = "date-time"), 
		example = "2019-10-30T12:00:00Z"
)
@Parameter(
		in = ParameterIn.QUERY,
		name = "dataCriacaoFim",
		description = "Data/hora de criação final para filtro da pesquisa",
		schema = @Schema(implementation = OffsetDateTime.class),
		example = "2019-10-31T12:00:00Z"
)
public @interface PedidoFiltroParameter {

}
