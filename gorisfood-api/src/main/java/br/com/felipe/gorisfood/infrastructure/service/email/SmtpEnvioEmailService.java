package br.com.felipe.gorisfood.infrastructure.service.email;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import br.com.felipe.gorisfood.core.email.EmailProperties;
import br.com.felipe.gorisfood.domain.service.EnvioEmailService;
import freemarker.template.Configuration;

public class SmtpEnvioEmailService implements EnvioEmailService {

	@Autowired
	protected EmailProperties emailProperties;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private ProcessadorEmailTemplate processadorEmailTemplate;
	
	
	@Override
	public void enviar(Mensagem mensagem) {
		try {
			var mimeMessage = criarMimeMessage(mensagem);
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Não foi possível enviar e-mail", e);
		}
	}
	
	protected MimeMessage criarMimeMessage(Mensagem msg) throws MessagingException {
		var mimeMessage = mailSender.createMimeMessage();
		var helper = new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.name());
		var corpo = processadorEmailTemplate.processarTemplate(msg);
		
		helper.setFrom(emailProperties.getRemetente());
		helper.setTo(msg.getDestinatarios().toArray(new String[0]));
		helper.setSubject(msg.getAssunto());
		helper.setText(corpo, true);
		
		return mimeMessage;
	}
	
}
