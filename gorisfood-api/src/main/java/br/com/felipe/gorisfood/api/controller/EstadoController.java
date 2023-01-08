package br.com.felipe.gorisfood.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import br.com.felipe.gorisfood.api.assembler.EstadoRequestDtoDesassembler;
import br.com.felipe.gorisfood.api.assembler.EstadoResponseAssembler;
import br.com.felipe.gorisfood.api.model.request.EstadoRequestDTO;
import br.com.felipe.gorisfood.api.model.response.EstadoResponseDTO;
import br.com.felipe.gorisfood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(value = "estados", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController {

	@Autowired
	private CadastroEstadoService service;
	
	@Autowired
	private EstadoResponseAssembler assembler;
	
	@Autowired
	private EstadoRequestDtoDesassembler desassembler;
	
	@GetMapping(consumes = MediaType.ALL_VALUE)
	public List<EstadoResponseDTO> listar() {
		return assembler.toDtoList(service.listar());
	}
	
	@GetMapping(value = "{id}", consumes = MediaType.ALL_VALUE)
	public EstadoResponseDTO buscar(@PathVariable Long id) {
		return assembler.toDto(service.buscar(id));
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public EstadoResponseDTO criar(@RequestBody @Valid EstadoRequestDTO estadoDTO) {
		return assembler.toDto(service.salvar(desassembler.toModel(estadoDTO)));
	}
	
	@PutMapping("{id}")
	public EstadoResponseDTO alterar(@PathVariable Long id, @RequestBody @Valid EstadoRequestDTO estadoDTO){
		var estado = desassembler.toModel(estadoDTO);
		return assembler.toDto(service.atualizar(id, estado));
	}
	
	@DeleteMapping(value = "{id}", consumes = MediaType.ALL_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		service.remover(id);
	}
}
