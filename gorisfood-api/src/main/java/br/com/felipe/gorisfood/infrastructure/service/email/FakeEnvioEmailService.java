package br.com.felipe.gorisfood.infrastructure.service.email;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.felipe.gorisfood.domain.service.EnvioEmailService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEnvioEmailService implements EnvioEmailService {

	@Autowired
	private ProcessadorEmailTemplate processadorEmailTemplate;
	
	@Override
	public void enviar(Mensagem mensagem) {
		try {
			var mimeMessage = processadorEmailTemplate.processarTemplate(mensagem);
			log.info("Disparando e-mail: ");
			log.info(mimeMessage);
			
		} catch (Exception e) {
			throw new EmailException("Não foi possível enviar e-mail", e);
		}
	}
}
