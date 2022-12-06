package br.com.felipe.gorisfood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.felipe.gorisfood.domain.exception.EntidadeEmUsoExcpetion;
import br.com.felipe.gorisfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.model.Cozinha;
import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.service.CadastroCozinhaService;
import br.com.felipe.gorisfood.domain.service.CadastroRestauranteService;

@SpringBootTest
class CadastroCozinhaIT {

	@Autowired
	private CadastroCozinhaService service;
	
	@Autowired
	private CadastroRestauranteService restauranteService;
	
	@Test
	void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
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
	void deveFalhar_QuandoCadastrarCozinhaSemNome() {
		var cozinha = new Cozinha();
		
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
}
