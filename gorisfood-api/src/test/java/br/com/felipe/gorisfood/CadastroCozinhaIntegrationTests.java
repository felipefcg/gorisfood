package br.com.felipe.gorisfood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.felipe.gorisfood.domain.model.Cozinha;
import br.com.felipe.gorisfood.domain.service.CadastroCozinhaService;

@SpringBootTest
class CadastroCozinhaIntegrationTests {

	@Autowired
	private CadastroCozinhaService service;
	
	@Test
	void testarCadastroCozinhaComSucesso() {
		//cenário
		var cozinha = new Cozinha();
		cozinha.setNome("Chinesa");
		
		//ação
		cozinha = service.salvar(cozinha);
		
		//validação
		assertThat(cozinha).isNotNull();
		assertThat(cozinha.getId()).isNotNull();
	}
	
	@Test
	void testarCadastroCozinhaSemNome() {
		var cozinha = new Cozinha();
		
		ConstraintViolationException erroEsperado = assertThrows(ConstraintViolationException.class, () -> service.salvar(cozinha));
		assertThat(erroEsperado).isNotNull();
		
	}

}
