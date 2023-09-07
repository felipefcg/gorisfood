package br.com.felipe.gorisfood.api.v1.model.request;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoIdRequestDTO {
	
	@Schema(example = "1")
	@NotNull
	private Long id;
}
