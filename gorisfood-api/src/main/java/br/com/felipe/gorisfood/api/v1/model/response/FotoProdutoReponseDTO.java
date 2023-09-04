package br.com.felipe.gorisfood.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoReponseDTO extends RepresentationModel<FotoProdutoReponseDTO> {
	

	private String nomeArquivo;
	

	private String descricao;
	

	private String contentType;
	

	private Long tamanho;
}
