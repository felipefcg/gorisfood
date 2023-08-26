package br.com.felipe.gorisfood.infrastructure.service.email;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import br.com.felipe.gorisfood.domain.service.EnvioEmailService.Mensagem;
import freemarker.template.Configuration;

@Component
public class ProcessadorEmailTemplate {

	@Autowired
	private Configuration freeMarkerConfig;
	
	public String processarTemplate(Mensagem mensagem) { 
		try {
			var freeMakerTemplate = freeMarkerConfig.getTemplate(mensagem.getTemplate(), StandardCharsets.UTF_8.name());
			return FreeMarkerTemplateUtils.processTemplateIntoString(freeMakerTemplate, mensagem.getVariaveis());
		} catch (Exception e) {
			throw new EmailException("Não foi possível montar o e-mail", e);
		}
	}
}
