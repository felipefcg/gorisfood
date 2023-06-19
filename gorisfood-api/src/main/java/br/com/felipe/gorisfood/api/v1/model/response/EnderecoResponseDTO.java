package br.com.felipe.gorisfood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoResponseDTO extends RepresentationModel<EnderecoResponseDTO> {
	
	@ApiModelProperty(example = "00000-000")
	private String cep;
	
	@ApiModelProperty(example = "Rua das pedreiras")
	private String logradouro;
	
	@ApiModelProperty(example = "1000")
	private String numero;
	
	@ApiModelProperty(example = "casa 2")
	private String complemento;
	
	@ApiModelProperty(example = "Vila dos Ypês")
	private String bairro;
	
	@ApiModelProperty(example = "Campinas")
	private String cidade;
	
	@ApiModelProperty(example = "São Paulo")
	private String estado;
}
