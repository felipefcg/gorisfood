package br.com.felipe.gorisfood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import br.com.felipe.gorisfood.domain.model.Cozinha;
import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.repository.CozinhaRepository;
import br.com.felipe.gorisfood.domain.repository.RestauranteRepository;
import br.com.felipe.gorisfood.util.DatabaseCleaner;
import br.com.felipe.gorisfood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class RestauranteControllerIT {

	private static final Long ID_RESTAURANTE_INEXISTENTE = 200L;
	private static final String TITULO_RESTAURANTE_INEXISTENTE = "Recurso Não Encontrado";
	private static final String MENSAGEM_RESTAURANTE_INEXISTENTE = "Restaurante não encontrado com o id %d.";
	
	private static final String TITULO_DADOS_INVALIDOS = "Dados inválidos";
	private static final String MENSAGEM_DADOS_INVALIDOS = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
	private static final String MENSAGEM_NOME_RESTAURANTE_OBRIGATORIO = "Nome do restaurante é obrigatório.";
	private static final String MENSAGEM_TAXA_FRETE_OBRIGATORIO = "Taxa Frete é obrigatório.";
	private static final String MENSAGEM_TAXA_FRETE_VALOR_INVALIDO = "O valor de Taxa Frete deve ser maior ou igual a 0.";
	private static final String MENSAGEM_COZINHA_OBRIGATORIA = "A cozinha é obrigatório.";
	
	private static final String TITULO_COZINHA_INEXISTENTE = "Entidade Dependente";
	private static final String REGEX_MENSAGEM_COZINHA_INEXISTENTE = "Cozinha não encontrada com id [0-9]+\\. ";
	
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	private Long quantidadeRegistros;
	private Restaurante restauranteExistente;
	
	@BeforeEach
	void setUp() throws Exception {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "restaurantes";
		
		prepararDados();
	}

	@Test
	void deveRetornarTotalRestaurantes_QuandoConsultarListaRestaurantes() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("", Matchers.hasSize(quantidadeRegistros.intValue()));
	}
	
	@Test
	void deveRetornar200_QuandoConsultarRestauranteExistente() {
		given()
			.accept(ContentType.JSON)
			.pathParam("restauranteId", restauranteExistente.getId())
		.when()
			.get("{restauranteId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(restauranteExistente.getNome()));
	}
	
	@Test
	void deveRetornar404_QuandoConsultarRestauranteInexistente() {
		given()
			.accept(ContentType.JSON)
			.pathParam("restauranteId", ID_RESTAURANTE_INEXISTENTE)
		.when()
			.get("{restauranteId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.body("detalhe", equalTo(String.format(MENSAGEM_RESTAURANTE_INEXISTENTE, ID_RESTAURANTE_INEXISTENTE)))
			.body("titulo", equalTo(TITULO_RESTAURANTE_INEXISTENTE));
	}
	
	@Test
	void deveRetornar201_QuandoCadastrarRestaurante() {
		String restaurante = ResourceUtils.getContentFromResource("json/correto/cadastro-restaurante.json");
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(restaurante)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	void deveRetornar400_QuandoRestauranteComNomeNuloOuBranco() {
		String restaurante = ResourceUtils.getContentFromResource("json/invalido/cadastro-restaurante-nome-invalido.json");
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(restaurante)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("titulo", equalTo(TITULO_DADOS_INVALIDOS))
			.body("detalhe", equalTo(MENSAGEM_DADOS_INVALIDOS))
			.body("objetos.erro", Matchers.hasSize(1))
			.body("objetos.erro[0]", equalTo(MENSAGEM_NOME_RESTAURANTE_OBRIGATORIO));
	}
	
	@Test
	void deveRetornar400_QuandoRestauranteComTaxaFreteNulo() {
		String restaurante = ResourceUtils.getContentFromResource("json/invalido/cadastro-restaurante-taxaFrete-nulo.json");
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(restaurante)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("titulo", equalTo(TITULO_DADOS_INVALIDOS))
			.body("detalhe", equalTo(MENSAGEM_DADOS_INVALIDOS))
			.body("objetos.erro", Matchers.hasSize(1))
			.body("objetos.erro[0]", equalTo(MENSAGEM_TAXA_FRETE_OBRIGATORIO));
	}
	
	@Test
	void deveRetornar400_QuandoRestauranteComTaxaFreteZeroOuNegativo() {
		String restaurante = ResourceUtils.getContentFromResource("json/invalido/cadastro-restaurante-taxaFrete-invalido.json");
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(restaurante)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("titulo", equalTo(TITULO_DADOS_INVALIDOS))
			.body("detalhe", equalTo(MENSAGEM_DADOS_INVALIDOS))
			.body("objetos.erro", Matchers.hasSize(1))
			.body("objetos.erro[0]", equalTo(MENSAGEM_TAXA_FRETE_VALOR_INVALIDO));
	}
	
	@Test
	void deveRetornar400_QuandoRestauranteComCozinhaNula() {
		String restaurante = ResourceUtils.getContentFromResource("json/invalido/cadastro-restaurante-cozinha-invalida.json");
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(restaurante)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("titulo", equalTo(TITULO_DADOS_INVALIDOS))
			.body("detalhe", equalTo(MENSAGEM_DADOS_INVALIDOS))
			.body("objetos.erro", Matchers.hasSize(1))
			.body("objetos.erro[0]", equalTo(MENSAGEM_COZINHA_OBRIGATORIA));
	}
	@Test
	void deveRetornar422_QuandoCadastrarRestauranteComCozinhaInexistente() {
		String restaurante = ResourceUtils.getContentFromResource("json/invalido/cadastro-restaurante-cozinha-inexistente.json");
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(restaurante)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
			.body("titulo", equalTo(TITULO_COZINHA_INEXISTENTE))
			.body("detalhe", Matchers.matchesRegex(REGEX_MENSAGEM_COZINHA_INEXISTENTE));
	}
	
	private void prepararDados() {
		databaseCleaner.clearTables();
		
		var cozinha = new Cozinha();
		cozinha.setId(1L);
		cozinha.setNome("Chinesa");
		cozinhaRepository.save(cozinha);
		
		var restaurante1 = new Restaurante();
		restaurante1.setNome("Restaurante XenXen");
		restaurante1.setTaxaFrete(new BigDecimal(5));
		restaurante1.setCozinha(cozinha);
		restauranteExistente = restauranteRepository.save(restaurante1);
		
		quantidadeRegistros = restauranteRepository.count();
	}
	
	

}
