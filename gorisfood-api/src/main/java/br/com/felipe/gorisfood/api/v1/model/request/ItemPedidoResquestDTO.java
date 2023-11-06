package br.com.felipe.gorisfood.api.v1.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoResquestDTO {

	@Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
	@NotNull
	private Long produtoId;
	
	@Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
	@NotNull
	@Positive
	private Integer quantidade;
	
	@Schema(example = "Sem cebola", requiredMode = RequiredMode.NOT_REQUIRED)
	private String observacao;
}
