package br.com.felipe.gorisfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.felipe.gorisfood.GorisfoodApiApplication;
import br.com.felipe.gorisfood.domain.repository.RestauranteRepository;

public class BuscaRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(GorisfoodApiApplication.class)
										.web(WebApplicationType.NONE)
										.run(args);
		
		RestauranteRepository restauranteRepository = context.getBean(RestauranteRepository.class);
		
		System.out.println(restauranteRepository.buscar(1L));
	}

}
