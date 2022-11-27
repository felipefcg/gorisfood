package br.com.felipe.gorisfood.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	public Cozinha buscar (@PathVariable Long id) {
		return service.buscar(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha criar(@RequestBody @Valid Cozinha cozinha) {
		return service.salvar(cozinha);
	}
	
	@PutMapping("{id}")
	public Cozinha alterar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
		Cozinha cozinhaAtual = service.buscar(id);
		
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		return service.salvar(cozinhaAtual);
			
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		service.remover(id);

	}
	
	@GetMapping(value = "primeiro", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public Optional<Cozinha> buscarPrimeiro() {
		return service.buscarPrimeiro();
	}
}
