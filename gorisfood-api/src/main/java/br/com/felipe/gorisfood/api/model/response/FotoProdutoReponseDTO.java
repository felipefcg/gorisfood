package br.com.felipe.gorisfood.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoReponseDTO {
	
	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;
}
