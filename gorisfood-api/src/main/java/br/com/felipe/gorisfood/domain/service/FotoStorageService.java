package br.com.felipe.gorisfood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {
	void armazenar(NovaFoto novaFoto);
	void remover(String nomeArquivo);
	FotoRecuperada recuperar(String nomaArquivo);
	
	default String gerarNomeArquivo(String nomeArquivo) {
		return String.format("%s_%s", UUID.randomUUID().toString(), nomeArquivo);
	}
	
	default void substituir(String nomeArquivoExistente, NovaFoto novaFoto) {
		
		if(nomeArquivoExistente != null) {
			remover(nomeArquivoExistente);
		}
		
		armazenar(novaFoto);
	}
	
	@Getter
	@Builder
	public class NovaFoto {
		private String nomeArquivo;
		private String contentType;
		private Long contentLength;
		private InputStream inputStream;
	}

	@Getter
	@Builder
	public class FotoRecuperada {
		private InputStream inputStream;
		private String url;
		
		public boolean temUrl() {
			return url != null;
		}
		
		public boolean temInputStream() {
			return inputStream != null;
		}
	}
	
}
