package br.com.felipe.gorisfood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import br.com.felipe.gorisfood.domain.exception.EntidadeEmUsoExcpetion;
import br.com.felipe.gorisfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.model.Cozinha;
import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.repository.CozinhaRepository;
import br.com.felipe.gorisfood.domain.repository.RestauranteRepository;
import br.com.felipe.gorisfood.domain.service.CadastroCozinhaService;
import br.com.felipe.gorisfood.util.DatabaseCleaner;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {
	
	@Autowired
	private CadastroCozinhaService service;

	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Test
	void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
		//cenário
//		var cozinha = new Cozinha();
//		cozinha.setNome("Chinesa");
		var cozinha = Cozinha.builder().nome("Chinesa").build();
		//ação
		cozinha = service.salvar(cozinha);
		
		//validação
		assertThat(cozinha).isNotNull();
		assertThat(cozinha.getId()).isNotNull();
	}
	
	@BeforeEach
	void setup() {
		databaseCleaner.clearTables();
		prepararDados();
	}
	@Test
	void deveFalhar_QuandoCadastrarCozinhaSemNome() {
//		var cozinha = new Cozinha();
		var cozinha = Cozinha.builder().build();		
		
		ConstraintViolationException erroEsperado = assertThrows(ConstraintViolationException.class, () -> service.salvar(cozinha));
		assertThat(erroEsperado).isNotNull();
		
	}
	
	@Test
	void deveFalhar_QuandoExcluirCozinhaEmUso() {
		
		 assertThrows(EntidadeEmUsoExcpetion.class, () -> service.remover(1L));
		
	}

	@Test
	void deveFalhar_QuandoExcluirCozinhaInexistente() {
		assertThrows(EntidadeNaoEncontradaException.class, () -> service.remover(0L));
	}
	
	private void prepararDados() {
//		var cozinha1 = new Cozinha();
//		cozinha1.setNome("Tailandesa");
		var cozinha1 = Cozinha.builder().nome("Tailandesa").build();
		cozinha1 = cozinhaRepository.save(cozinha1);
		
//		var cozinha2 = new Cozinha();
//		cozinha2.setNome("Indiana");
		var cozinha2 = Cozinha.builder().nome("Indiana").build();
		cozinhaRepository.save(cozinha2);
		
//		var restaurante1 = new Restaurante();
//		restaurante1.setNome("Restaurante 1");
//		restaurante1.setCozinha(cozinha1);
//		restaurante1.setTaxaFrete(new BigDecimal(20));
		var restaurante1 = Restaurante.builder()
								.nome("Restaurante 1")
								.cozinha(cozinha1)
								.taxaFrete(new BigDecimal(20))
								.build();

		restauranteRepository.save(restaurante1);
	}
}
