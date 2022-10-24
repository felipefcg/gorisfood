package br.com.felipe.gorisfood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.domain.exception.EntidadeEmUsoExcpetion;
import br.com.felipe.gorisfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.model.Cozinha;
import br.com.felipe.gorisfood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("cozinhas")
public class CozinhaController {

	@Autowired
	private CadastroCozinhaService service;
	
	@GetMapping
	public List<Cozinha> listar () {
		return service.listar();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Cozinha> buscar (@PathVariable Long id) {
		try {
			Cozinha cozinha = service.buscar(id);
			return ResponseEntity.ok(cozinha);
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha criar(@RequestBody Cozinha cozinha) {
		return service.salvar(cozinha);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Cozinha> alterar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
		try {
			Cozinha cozinhaAtual = service.buscar(id);
			
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			cozinha = service.salvar(cozinhaAtual);
			
			return ResponseEntity.ok(cozinha);
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity remover(@PathVariable Long id) {
		try {
			service.remover(id);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoExcpetion e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
			
		}
	}
}
