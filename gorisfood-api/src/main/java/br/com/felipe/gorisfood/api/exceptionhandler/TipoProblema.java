package br.com.felipe.gorisfood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum TipoProblema {
	ENTIDADE_NAO_ENCONTRADA("Entidade Não Encontrada", "entidade-nao-encontrada"),
	ENTIDADE_EM_USO("Entidade em Uso","entidade-em-uso"),
	ENTIDADE_RELACIONAMENTO_NAO_ENCONTRADA("Entidade Dependente","entidade-dependente"),
	CORPO_REQUISCAO_INVALIDO("Corpo da Requisição Inválido", "corpo-da-requisicao-invalido");
	
	private String uri;
	private String titulo;
	
	private TipoProblema(String titulo, String path) {
		this.titulo = titulo;
		this.uri = String.format("https://api.gorisfood.com.br/%s", path);
		
	}
}
