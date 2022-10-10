package br.com.felipe.gorisfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.felipe.gorisfood.GorisfoodApiApplication;
import br.com.felipe.gorisfood.domain.model.Cozinha;
import br.com.felipe.gorisfood.domain.repository.CozinhaRepository;

public class AlteracaoCozinhaMain {

	public static void main(String[] args) {
		 
		ApplicationContext context = new SpringApplicationBuilder(GorisfoodApiApplication.class).web(WebApplicationType.NONE).run(args);
	
		CozinhaRepository cozinhaRepository = context.getBean(CozinhaRepository.class);
		
		
		Cozinha cozinha = cozinhaRepository.buscar(1L);
		System.out.println("Cozinha antes do update: " + cozinha);
		
		cozinha.setNome("Brasileira");
	
		cozinha = cozinhaRepository.salvar(cozinha);
		
		System.out.println("Cozinha depois do update: " + cozinha);		
	}

}

