package br.com.felipe.gorisfood.api.v1.model.response;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.felipe.gorisfood.api.v1.model.view.RestauranteView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteResponseDTO extends RepresentationModel<RestauranteResponseDTO> {
	
	@ApiModelProperty(example = "1")
//	@JsonView({
//		RestauranteView.Resumo.class,
//		RestauranteView.ApenasNome.class
//	})
	private Long id;
	
	@ApiModelProperty(example = "Thai Gourmet")
//	@JsonView({
//		RestauranteView.Resumo.class,
//		RestauranteView.ApenasNome.class
//	})
	private String nome;
	
	@ApiModelProperty(example = "19.99")
//	@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(example = "true")
	private Boolean ativo;
	
	@ApiModelProperty(example = "false")
	private Boolean aberto;
	
//	@JsonView(RestauranteView.Resumo.class)
	private CozinhaResponseDTO cozinha;
	private EnderecoResponseDTO endereco;
}
