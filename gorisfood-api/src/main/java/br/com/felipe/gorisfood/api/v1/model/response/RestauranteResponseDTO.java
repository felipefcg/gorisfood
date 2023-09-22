package br.com.felipe.gorisfood.api.v1.model.response;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteResponseDTO extends RepresentationModel<RestauranteResponseDTO> {
	

//	@JsonView({
//		RestauranteView.Resumo.class,
//		RestauranteView.ApenasNome.class
//	})
	@Schema(example = "1")
	private Long id;
	

//	@JsonView({
//		RestauranteView.Resumo.class,
//		RestauranteView.ApenasNome.class
//	})
	@Schema(example = "Thai Gourmet")
	private String nome;
	

//	@JsonView(RestauranteView.Resumo.class)
	@Schema(example = "19.99")
	private BigDecimal taxaFrete;
	
	@Schema(example = "true")
	private Boolean ativo;
	
	@Schema(example = "false")
	private Boolean aberto;
	
//	@JsonView(RestauranteView.Resumo.class)
	
	@Schema(description = "Dados do tipo de cozinha do restaurante")
	private CozinhaResponseDTO cozinha;
	
	@Schema(description = "Endereço do restaurante", requiredMode = RequiredMode.NOT_REQUIRED)
	private EnderecoResponseDTO endereco;
}
