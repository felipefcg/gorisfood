package br.com.felipe.gorisfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import br.com.felipe.gorisfood.GorisfoodApiApplication;
import br.com.felipe.gorisfood.domain.model.Cozinha;

public class InsereCozinhaMain {

	public static void main(String[] args) {
		 
		ApplicationContext context = new SpringApplicationBuilder(GorisfoodApiApplication.class).web(WebApplicationType.NONE).run(args);
		
		Cozinha brasileira = new Cozinha();
		brasileira.setNome("Brasileira");
		
		Cozinha japonesa = new Cozinha();
		japonesa.setNome("Japonesa");
		
		CadastroCozinha cadastroCozinha = context.getBean(CadastroCozinha.class);
		
		brasileira = cadastroCozinha.inserir(brasileira);
		japonesa = cadastroCozinha.inserir(japonesa);
		
		System.out.printf("%d - %s\n", brasileira.getId(), brasileira.getNome());
		System.out.printf("%d - %s\n", japonesa.getId(), japonesa.getNome());
	}

}

