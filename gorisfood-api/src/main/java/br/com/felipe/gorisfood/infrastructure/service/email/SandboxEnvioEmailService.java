package br.com.felipe.gorisfood.infrastructure.service.email;

public class SandboxEnvioEmailService extends SmtpEnvioEmailService {

	@Override
	public void enviar(Mensagem mensagem) {
		
		mensagem = Mensagem.builder()
				.destinatario(emailProperties.getSandbox().getDestinatario())
				.assunto(mensagem.getAssunto())
				.template(mensagem.getTemplate())
				.variaveis(mensagem.getVariaveis())
				.build();
		
		super.enviar(mensagem);
			
	}
}
