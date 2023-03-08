package br.com.felipe.gorisfood.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.core.email.EmailProperties.TipoImplementacaoEnvioEmail;
import br.com.felipe.gorisfood.domain.service.EnvioEmailService;
import br.com.felipe.gorisfood.infrastructure.service.email.FakeEnvioEmailService;
import br.com.felipe.gorisfood.infrastructure.service.email.SandboxEnvioEmailService;
import br.com.felipe.gorisfood.infrastructure.service.email.SmtpEnvioEmailService;

@Component
public class EmailConfig {

	@Autowired
	private EmailProperties emailProperties;
	
	@Bean
	public EnvioEmailService envioEmailService() {
	
		switch (emailProperties.getImpl()) {
		case SMTP:
			return new SmtpEnvioEmailService();
		case SANDBOX:
			return new SandboxEnvioEmailService();
		default:
			return new FakeEnvioEmailService();
		}
		
//		if(TipoImplementacaoEnvioEmail.SMTP.equals(emailProperties.getImpl())) {
//			return new SmtpEnvioEmailService();
//		}
//		
//		if(TipoImplementacaoEnvioEmail.SANDBOX.equals(emailProperties.getImpl())) {
//			return new SandboxEnvioEmailService();
//		}
//		
//		return new FakeEnvioEmailService();
	}
}
