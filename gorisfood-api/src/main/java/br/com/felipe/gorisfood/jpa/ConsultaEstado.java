package br.com.felipe.gorisfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.felipe.gorisfood.GorisfoodApiApplication;
import br.com.felipe.gorisfood.domain.repository.EstadoRepository;

public class ConsultaEstado {

	public static void main(String[] args) {

		 
		ApplicationContext context = new SpringApplicationBuilder(GorisfoodApiApplication.class).web(WebApplicationType.NONE).run(args);
		
		EstadoRepository repository = context.getBean(EstadoRepository.class);
		repository.listar().forEach(System.out::println);
	
	}

}
