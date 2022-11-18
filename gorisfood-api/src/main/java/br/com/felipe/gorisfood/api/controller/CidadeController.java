package br.com.felipe.gorisfood.api.controller;

import java.util.List;

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
import org.springframework.web.server.ResponseStatusException;

import br.com.felipe.gorisfood.domain.exception.EstadoNaoEncontradoException;
import br.com.felipe.gorisfood.domain.model.Cidade;
import br.com.felipe.gorisfood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping(value = "cidades",
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController {

	@Autowired
	private CadastroCidadeService service;
	
	@GetMapping(consumes = MediaType.ALL_VALUE)
	public List<Cidade> listar() {
		return service.listar();
	}
	
	@GetMapping(value = "{id}", consumes = MediaType.ALL_VALUE)
	public Cidade buscar(@PathVariable Long id) {
		return service.buscar(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade criar(@RequestBody Cidade cidade) {
		try {
			return  service.salvar(cidade);
		} catch (EstadoNaoEncontradoException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
		}
	}
	
	@PutMapping("{id}")
	public Cidade alterar(@PathVariable Long id, @RequestBody Cidade cidade) {
		return service.alterar(id, cidade);
	}
	
	@DeleteMapping(value = "{id}", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
			service.remover(id);
	}
}
