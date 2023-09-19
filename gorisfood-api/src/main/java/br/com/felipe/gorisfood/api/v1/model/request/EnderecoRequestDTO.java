package br.com.felipe.gorisfood.api.v1.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.felipe.gorisfood.core.validation.CEP;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequestDTO {
	
	@Schema(example = "00000-000", requiredMode = RequiredMode.REQUIRED)
	@NotBlank
	@CEP
	private String cep;
	
	@Schema(example = "Rua das pedreiras", requiredMode = RequiredMode.REQUIRED)
	@NotBlank
	private String logradouro;
	
	@Schema(example = "1", requiredMode = RequiredMode.REQUIRED)
	@NotBlank
	private String numero;
	
	@Schema(example = "S/N", requiredMode = RequiredMode.NOT_REQUIRED)
	private String complemento;
	
	@Schema(example = "Vila dos Ypês", requiredMode = RequiredMode.REQUIRED)
	@NotBlank
	private String bairro;
	
	@Schema(description = "Dados da cidade", requiredMode = RequiredMode.REQUIRED)
	@Valid
	@NotNull
	private CidadeIdRequestDTO cidade;
}
