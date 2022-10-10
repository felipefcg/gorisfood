package br.com.felipe.gorisfood.jpa;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.felipe.gorisfood.GorisfoodApiApplication;
import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.repository.RestauranteRepository;

public class AlteracaoRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(GorisfoodApiApplication.class)
										.web(WebApplicationType.NONE)
										.run(args);
		
		Restaurante restaurante = new Restaurante();
		restaurante.setId(1L);
		restaurante.setNome("Mc Donald's");
		restaurante.setTaxaFrete(new BigDecimal(1.05));

		RestauranteRepository restauranteRepository = context.getBean(RestauranteRepository.class);
		restauranteRepository.salvar(restaurante);
	}

}
