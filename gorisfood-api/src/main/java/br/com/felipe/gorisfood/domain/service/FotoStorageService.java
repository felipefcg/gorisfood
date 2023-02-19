package br.com.felipe.gorisfood.domain.service;

import com.lowagie.text.pdf.codec.Base64.InputStream;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {
	void armazenar(NovaFoto novaFoto);
	
	@Getter
	@Builder
	public class NovaFoto {
		private String nomeArquivo;
		private InputStream inputStream;
	}
}
