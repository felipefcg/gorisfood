package br.com.felipe.gorisfood.api.v1.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.felipe.gorisfood.core.validation.CEP;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequestDTO {
	
	@ApiModelProperty(example = "00000-000", required = true)
	@NotBlank
	@CEP
	private String cep;
	
	@ApiModelProperty(example = "Rua das pedreiras", required = true)
	@NotBlank
	private String logradouro;
	
	@ApiModelProperty(example = "S/N", required = true)
	@NotBlank
	private String numero;
	
	@ApiModelProperty(example = "casa 2")
	private String complemento;
	
	@ApiModelProperty(example = "Vila dos Ypês", required = true)
	@NotBlank
	private String bairro;
	
	@Valid
	@NotNull
	private CidadeIdRequestDTO cidade;
}
