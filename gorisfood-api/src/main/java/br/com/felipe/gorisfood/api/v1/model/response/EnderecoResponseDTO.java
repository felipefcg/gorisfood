package br.com.felipe.gorisfood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoResponseDTO extends RepresentationModel<EnderecoResponseDTO> {
	

	private String cep;
	

	private String logradouro;
	

	private String numero;
	

	private String complemento;
	

	private String bairro;
	

	private String cidade;
	

	private String estado;
}
