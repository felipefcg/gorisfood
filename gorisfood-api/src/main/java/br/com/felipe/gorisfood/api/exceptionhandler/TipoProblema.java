package br.com.felipe.gorisfood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum TipoProblema {
	RECURSO_NAO_ENCONTRADO("Recurso Não Encontrado", "recurso-nao-encontrado"),
	ENTIDADE_EM_USO("Entidade em Uso","entidade-em-uso"),
	ENTIDADE_RELACIONAMENTO_NAO_ENCONTRADA("Entidade Dependente","entidade-dependente"),
	CORPO_REQUISCAO_INVALIDO("Corpo da Requisição Inválido", "corpo-da-requisicao-invalido"),
	PARAMETRO_INVALIDO("Parametro Inválido", "parametro-invalido"),
	ERRO_DE_SISTEMA("Erro de Sistema", "erro-de-sistema"),
	DADOS_INVALIDOS("Dados inválidos", "dados-invalidos"),
	ACESSO_NEGADO("Acesso Negado", "acesso-negado");
	
	private String uri;
	private String titulo;
	
	private TipoProblema(String titulo, String path) {
		this.titulo = titulo;
		this.uri = String.format("https://api.gorisfood.com.br/%s", path);
		
	}
}
