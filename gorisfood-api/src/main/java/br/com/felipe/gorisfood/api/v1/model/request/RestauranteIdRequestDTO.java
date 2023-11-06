package br.com.felipe.gorisfood.api.v1.model.request;

import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteIdRequestDTO {

	@NotNull
	@Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
	private Long id;
}
