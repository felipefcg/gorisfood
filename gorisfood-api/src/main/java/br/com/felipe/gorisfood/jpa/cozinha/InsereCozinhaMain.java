package br.com.felipe.gorisfood.jpa.cozinha;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.felipe.gorisfood.GorisfoodApiApplication;
import br.com.felipe.gorisfood.domain.model.Cozinha;
import br.com.felipe.gorisfood.domain.repository.CozinhaRepository;

public class InsereCozinhaMain {

	public static void main(String[] args) {
		 
		ApplicationContext context = new SpringApplicationBuilder(GorisfoodApiApplication.class).web(WebApplicationType.NONE).run(args);
		
		Cozinha brasileira = new Cozinha();
		brasileira.setNome("Brasileira");
		
		Cozinha japonesa = new Cozinha();
		japonesa.setNome("Japonesa");
		
		CozinhaRepository cozinhaRepository = context.getBean(CozinhaRepository.class);
		
		brasileira = cozinhaRepository.salvar(brasileira);
		japonesa = cozinhaRepository.salvar(japonesa);
		
		System.out.printf("%d - %s\n", brasileira.getId(), brasileira.getNome());
		System.out.printf("%d - %s\n", japonesa.getId(), japonesa.getNome());
	}

}

