package br.com.felipe.gorisfood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {
	void armazenar(NovaFoto novaFoto);
	void remover(String nomeArquivo);
	InputStream recuperar(String nomaArquivo);
	
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
		private InputStream inputStream;
	}

	
}
