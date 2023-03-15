package br.com.felipe.gorisfood.client;

import java.math.BigDecimal;

import org.springframework.web.client.RestTemplate;

import br.com.felipe.gorisfood.client.api.ClientApiException;
import br.com.felipe.gorisfood.client.api.RestauranteClient;
import br.com.felipe.gorisfood.client.api.input.CidadeInput;
import br.com.felipe.gorisfood.client.api.input.CozinhaInput;
import br.com.felipe.gorisfood.client.api.input.EnderecoInput;
import br.com.felipe.gorisfood.client.api.input.RestauranteInput;

public class InclusaoRestauranteMain {

	public static void main(String[] args) {
		try {
			var cozinhaInput = CozinhaInput.builder()
								.id(1L)
								.build();
			var cidadeInput = CidadeInput.builder()
								.id(2L)
								.build();
			
			var enderecoInput = EnderecoInput.builder()
									.cep("07123-456")
									.logradouro("Rua dos Sem Rumo")
									.numero("S/N")
									.bairro("Jardim Sem Nome")
									.cidade(cidadeInput)
									.build();
			
			var restauranteInput = RestauranteInput.builder()
									.nome("Teste Cadastro Resturante")
									.taxaFrete(new BigDecimal("19.99"))
									.cozinha(cozinhaInput)
									.endereco(enderecoInput)
									.build();
			
			var restauranteClient = new RestauranteClient(new RestTemplate(), "http://localhost:8080");
			var restauranteModel = restauranteClient.adicionar(restauranteInput);
			System.out.println(restauranteModel);
		} catch (ClientApiException e) {
			if(e.getProblema() != null) {
				System.out.println(e.getProblema());
				e.getProblema().getObjetos()
					.forEach(o -> System.out.println(" - "+o.getErro()));
				return;
			}
			
			System.err.println("Erro Desconhecido");
			e.printStackTrace();
		}

	}

}
