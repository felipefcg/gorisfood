package br.com.felipe.gorisfood.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoResponseDTO {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Pagamento")
	private String nome;
	
	@ApiModelProperty(example = "Permite aprovar os pagamentos")
	private String descricao;
}
