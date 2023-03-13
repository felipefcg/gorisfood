package br.com.felipe.gorisfood.client;

import org.springframework.web.client.RestTemplate;

import br.com.felipe.gorisfood.client.api.RestauranteClient;

public class GorisfoodJavaClientApplication {

	public static void main(String[] args) {
		var restTemplate = new RestTemplate();
		var restauranteClient = new RestauranteClient(restTemplate, "http://localhost:8080");
		restauranteClient.listar()
			.forEach(System.out::println);
	}

}
