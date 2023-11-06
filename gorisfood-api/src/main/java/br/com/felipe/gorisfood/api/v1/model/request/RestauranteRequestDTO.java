package br.com.felipe.gorisfood.api.v1.model.request;

import java.math.BigDecimal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteRequestDTO {
	
	@Schema(description = "Nome do restaurante", example = "Thai Gourmet", requiredMode = RequiredMode.REQUIRED)
	@NotBlank
	private String nome;
	
	@Schema(description = "Taxa de frete cobrada por entrega",example = "9.99", requiredMode = RequiredMode.REQUIRED)
	@NotNull
	@PositiveOrZero
	private BigDecimal taxaFrete;
	
	@Schema(description = "Dados do tipo de cozinha do restaurante", requiredMode = RequiredMode.REQUIRED)
	@Valid
	@NotNull
	private CozinhaIdRequestDTO cozinha;
	
	@Schema(description = "Dados do enderecço do restaurante", requiredMode = RequiredMode.NOT_REQUIRED)
	@Valid
	@NotNull
	private EnderecoRequestDTO endereco;
	
}
