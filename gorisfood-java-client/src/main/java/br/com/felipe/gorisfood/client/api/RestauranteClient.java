package br.com.felipe.gorisfood.client.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import br.com.felipe.gorisfood.client.model.RestauranteResumoModel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestauranteClient {

	private static final String RESOURCE_PATH = "/restaurantes";
	private RestTemplate restTemplate;
	private String url;
	
	public List<RestauranteResumoModel> listar() {
		try {
			var restaurantes = restTemplate.getForObject(url+RESOURCE_PATH, RestauranteResumoModel[].class);
			return Arrays.asList(restaurantes);
		} catch (RestClientResponseException e) {
			throw new ClientApiException(e.getMessage	(), e);
		}
	}
}
