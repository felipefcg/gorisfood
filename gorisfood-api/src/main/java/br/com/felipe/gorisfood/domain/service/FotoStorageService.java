package br.com.felipe.gorisfood.domain.service;

import java.io.InputStream;
import java.util.UUID;


import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {
	void armazenar(NovaFoto novaFoto);
	
	default String gerarNomeArquivo(String nomeArquivo) {
		return String.format("%s_%s", UUID.randomUUID().toString(), nomeArquivo);
	}
	
	@Getter
	@Builder
	public class NovaFoto {
		private String nomeArquivo;
		private InputStream inputStream;
	}
}
