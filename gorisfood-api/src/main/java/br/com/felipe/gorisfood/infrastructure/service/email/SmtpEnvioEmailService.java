package br.com.felipe.gorisfood.infrastructure.service.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.felipe.gorisfood.core.email.EmailProperties;
import br.com.felipe.gorisfood.domain.service.EnvioEmailService;

@Service
public class SmtpEnvioEmailService implements EnvioEmailService{

	@Autowired
	private EmailProperties emailProperties;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void enviar(Mensagem mensagem) {
		try {
			var mimeMessage = criarMimeMessage(mensagem);
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Não foi possível enviar e-mail", e);
		}
	}
	
	private MimeMessage criarMimeMessage(Mensagem msg) throws MessagingException {
		var mimeMessage = mailSender.createMimeMessage();
		var helper = new MimeMessageHelper(mimeMessage);
		
		helper.setFrom(emailProperties.getRemetente());
		helper.setTo(msg.getDestinatarios().toArray(new String[0]));
		helper.setSubject(msg.getAssunto());
		helper.setText(msg.getCorpo(), true);
		
		return mimeMessage;
	}

}
