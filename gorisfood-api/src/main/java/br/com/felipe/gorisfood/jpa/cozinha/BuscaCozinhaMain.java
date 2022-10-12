package br.com.felipe.gorisfood.jpa.cozinha;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.felipe.gorisfood.GorisfoodApiApplication;
import br.com.felipe.gorisfood.domain.repository.CozinhaRepository;

public class BuscaCozinhaMain {

	public static void main(String[] args) {
		 
		ApplicationContext context = new SpringApplicationBuilder(GorisfoodApiApplication.class).web(WebApplicationType.NONE).run(args);
		
		CozinhaRepository cozinhaRepository = context.getBean(CozinhaRepository.class);
		System.out.println(cozinhaRepository.buscar(1L));
	}

}
