package br.com.felipe.gorisfood.domain.service;

import java.util.Map;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

public interface EnvioEmailService {
	void enviar(Mensagem mensagem);
	
	@Builder
	@Getter
	public class Mensagem {
		@Singular
		private Set<String> destinatarios;
		
		@NonNull
		private String assunto;
		
		@NonNull
		private String template;
		
		@Singular("variavel")
		private Map<String, Object> variaveis;
	}
}
