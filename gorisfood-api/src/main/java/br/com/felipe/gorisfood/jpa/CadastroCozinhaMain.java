package br.com.felipe.gorisfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import br.com.felipe.gorisfood.GorisfoodApiApplication;
import br.com.felipe.gorisfood.domain.model.Cozinha;

public class CadastroCozinhaMain {

	public static void main(String[] args) {
		 
		ApplicationContext context = new SpringApplicationBuilder(GorisfoodApiApplication.class).web(WebApplicationType.NONE).run(args);
		
		CadastroCozinha cozinha = context.getBean(CadastroCozinha.class);
		cozinha.listar().forEach(c -> System.out.println(c.getNome()));
	}

}
