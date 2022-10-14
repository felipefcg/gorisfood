package br.com.felipe.gorisfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.domain.model.Cozinha;
import br.com.felipe.gorisfood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository repository;
	
	@GetMapping
	public List<Cozinha> listar () {
		return repository.listar();
	}
	
	@GetMapping("{id}")
	public Cozinha buscar (@PathVariable Long id) {
		return repository.buscar(id);
	}
}
