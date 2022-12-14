package br.com.felipe.gorisfood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

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
import br.com.felipe.gorisfood.domain.repository.CozinhaRepository;
import br.com.felipe.gorisfood.util.DatabaseCleaner;
import br.com.felipe.gorisfood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CozinhaControllerIT {

	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	private static final int ID_COZINHA_INEXISTENTE = 100;
	private int totalCozinhasCadastradas;
	private Cozinha cozinhaAmericana;
	
	@BeforeEach
	void setup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
		databaseCleaner.clearTables();
		prepararDados();
	}
	
	@Test
	void deveRetornarStatus200_QuandoConsultarCozinhas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void deveRetornarTotalCozinhas_QuandoConsultarCozinhas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(totalCozinhasCadastradas));
	}
	
	@Test
	void deveRetornarStatus201_QuandoCadastrarCozinha() {
		var cozinha = new Cozinha();
		cozinha.setNome("Chinesa");
		
		String json = ResourceUtils.getContentFromResource("json/correto/cadastro-cozinha-chinesa.json");
		
		given()
			.body(json)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornarRespostaEEstatusCorretos_QuandoConsultarCozinhaExistente() {
		given()
			.pathParam("cozinhaId", cozinhaAmericana.getId())
			.accept(ContentType.JSON)
		.when()
			.get("{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(cozinhaAmericana.getNome()));		
	}
	
	@Test
	void deveRetornar404_QuandoConsultarCozinhaInexistente() {
		given()
			.pathParam("cozinhaId", ID_COZINHA_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("{cozinhaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	private void prepararDados() {
		var cozinhaTailandesa = new Cozinha();
		cozinhaTailandesa.setNome("Tailandesa");
		cozinhaRepository.save(cozinhaTailandesa);
		
		cozinhaAmericana = new Cozinha();
		cozinhaAmericana.setNome("Americana");
		cozinhaRepository.save(cozinhaAmericana);
		
		this.totalCozinhasCadastradas = (int) cozinhaRepository.count();
		
	}
}
