package br.com.felipe.gorisfood.api.v1.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoRequestDTO {

	@Schema(example = "Porco com molho agridoce", requiredMode = RequiredMode.REQUIRED)
	@NotBlank
	private String nome;
	
	@Schema(example = "Deliciosa carne suína ao molho especial", requiredMode = RequiredMode.REQUIRED)
	@NotBlank
	private String descricao;
	
	@Schema(example = "78.90", requiredMode = RequiredMode.REQUIRED)
	@NotNull
	@PositiveOrZero
	private BigDecimal preco;
	
	@Schema(example = "true", requiredMode = RequiredMode.REQUIRED)
	@NotNull
	private Boolean ativo;
	
}
