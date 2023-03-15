package br.com.felipe.gorisfood.client.api.input;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EnderecoInput {
	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private CidadeInput cidade;
}
