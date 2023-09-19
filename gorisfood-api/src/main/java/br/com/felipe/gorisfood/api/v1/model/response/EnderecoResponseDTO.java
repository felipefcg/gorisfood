package br.com.felipe.gorisfood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoResponseDTO extends RepresentationModel<EnderecoResponseDTO> {
	
	@Schema(example = "")
	private String cep;
	
	@Schema(example = "")
	private String logradouro;
	
	@Schema(example = "")
	private String numero;
	
	@Schema(example = "")
	private String complemento;
	
	@Schema(example = "")
	private String bairro;
	
	@Schema(example = "")
	private String cidade;
	
	@Schema(example = "")
	private String estado;
}
