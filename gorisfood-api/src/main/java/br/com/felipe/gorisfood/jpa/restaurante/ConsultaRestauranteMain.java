package br.com.felipe.gorisfood.jpa.restaurante;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.felipe.gorisfood.GorisfoodApiApplication;
import br.com.felipe.gorisfood.domain.repository.RestauranteRepository;

public class ConsultaRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext appContext = new SpringApplicationBuilder(GorisfoodApiApplication.class).web(WebApplicationType.NONE).run(args);
		
		RestauranteRepository restauranteRepository = appContext.getBean(RestauranteRepository.class);
		
		restauranteRepository.listar().forEach(System.out::println);

	}

}
