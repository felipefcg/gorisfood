package br.com.felipe.gorisfood.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponseDTO {
	@ApiModelProperty(example = "1")
	private Long id; 
	
	@ApiModelProperty(example = "Fulano De Tal")
	private String nome;
	
	@ApiModelProperty(example = "fulado@email.com")
	private String email;
}
