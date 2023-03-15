package br.com.felipe.gorisfood.client;

import org.springframework.web.client.RestTemplate;

import br.com.felipe.gorisfood.client.api.ClientApiException;
import br.com.felipe.gorisfood.client.api.RestauranteClient;

public class GorisfoodJavaClientApplication {

	public static void main(String[] args) {

		try {
			var restTemplate = new RestTemplate();
			var restauranteClient = new RestauranteClient(restTemplate, "http://localhost:8080");
			restauranteClient.listar()
			.forEach(System.out::println);
		} catch (ClientApiException e) {
			if (e.getProblem() != null) {
				System.out.println(e.getProblem());
				System.out.println(e.getProblem().getMensagemUsuario());
				return;
			}
			
			System.err.println("Erro desconhecido");
			e.printStackTrace();
		}
	}

}
