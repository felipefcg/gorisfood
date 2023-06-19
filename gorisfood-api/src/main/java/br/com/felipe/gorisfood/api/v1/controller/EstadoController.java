package br.com.felipe.gorisfood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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

import br.com.felipe.gorisfood.api.v1.assembler.EstadoRequestDtoDesassembler;
import br.com.felipe.gorisfood.api.v1.assembler.EstadoResponseAssembler;
import br.com.felipe.gorisfood.api.v1.model.request.EstadoRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.EstadoResponseDTO;
import br.com.felipe.gorisfood.api.v1.openapi.controller.EstadoControllerOpenApi;
import br.com.felipe.gorisfood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(value = "estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi {

	@Autowired
	private CadastroEstadoService service;
	
	@Autowired
	private EstadoResponseAssembler assembler;
	
	@Autowired
	private EstadoRequestDtoDesassembler desassembler;
	
	@GetMapping
	public CollectionModel<EstadoResponseDTO> listar() {
		return assembler.toCollectionModel(service.listar());
	}
	
	@GetMapping(value = "{id}")
	public EstadoResponseDTO buscar(@PathVariable Long id) {
		return assembler.toModel(service.buscar(id));
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public EstadoResponseDTO criar(@RequestBody @Valid EstadoRequestDTO estadoDTO) {
		return assembler.toModel(service.salvar(desassembler.toModel(estadoDTO)));
	}
	
	@PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public EstadoResponseDTO alterar(@PathVariable Long id, @RequestBody @Valid EstadoRequestDTO estadoDTO){
		var estado = desassembler.toModel(estadoDTO);
		return assembler.toModel(service.atualizar(id, estado));
	}
	
	@DeleteMapping(value = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		service.remover(id);
	}
}
