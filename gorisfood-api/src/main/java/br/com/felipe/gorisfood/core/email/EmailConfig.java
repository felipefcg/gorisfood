package br.com.felipe.gorisfood.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.core.email.EmailProperties.TipoImplementacaoEnvioEmail;
import br.com.felipe.gorisfood.domain.service.EnvioEmailService;
import br.com.felipe.gorisfood.infrastructure.service.email.FakeEnvioEmailService;
import br.com.felipe.gorisfood.infrastructure.service.email.SmtpEnvioEmailService;

@Component
public class EmailConfig {

	@Autowired
	private EmailProperties emailProperties;
	
	@Bean
	public EnvioEmailService envioEmailService() {
	
		if(TipoImplementacaoEnvioEmail.SMTP.equals(emailProperties.getImpl())) {
			return new SmtpEnvioEmailService();
		}
		
		return new FakeEnvioEmailService();
	}
}
