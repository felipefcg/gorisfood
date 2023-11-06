package br.com.felipe.gorisfood.core.email;

import jakarta.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("gorisfood.email")
@Getter
@Setter
@Validated
public class EmailProperties {
	
	@NotBlank
	private String remetente;
	
	private TipoImplementacaoEnvioEmail impl = TipoImplementacaoEnvioEmail.FAKE;
	
	private Sandbox sandbox = new Sandbox();
	
	public enum TipoImplementacaoEnvioEmail {
		SMTP, FAKE, SANDBOX;
	}
	
	@Getter
	@Setter
	public class Sandbox {
		private String destinatario;
	}
	
}
