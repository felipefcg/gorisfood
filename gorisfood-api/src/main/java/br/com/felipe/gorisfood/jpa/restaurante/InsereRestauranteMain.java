package br.com.felipe.gorisfood.jpa.restaurante;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.felipe.gorisfood.GorisfoodApiApplication;
import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.repository.RestauranteRepository;

public class InsereRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(GorisfoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		RestauranteRepository restauranteRepository = context.getBean(RestauranteRepository.class);
		
		Restaurante restaurante = new Restaurante();
		restaurante.setNome("Mc Donald's");
		restaurante.setTaxaFrete(new BigDecimal(0.99).round(MathContext.DECIMAL32).setScale(2));
		
		restaurante= restauranteRepository.salvar(restaurante);
		
		System.out.println(restaurante);

	}

}
