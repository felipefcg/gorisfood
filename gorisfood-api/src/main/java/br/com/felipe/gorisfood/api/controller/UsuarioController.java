package br.com.felipe.gorisfood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.assembler.UsuarioRequestDtoDesassembler;
import br.com.felipe.gorisfood.api.assembler.UsuarioResponseDtoAssembler;
import br.com.felipe.gorisfood.api.model.request.SenhaRequestDTO;
import br.com.felipe.gorisfood.api.model.request.UsuarioComSenhaRequestDTO;
import br.com.felipe.gorisfood.api.model.request.UsuarioRequestDTO;
import br.com.felipe.gorisfood.api.model.response.UsuarioResponseDTO;
import br.com.felipe.gorisfood.api.openapi.controller.UsuarioControllerOpenApi;
import br.com.felipe.gorisfood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(value =  "usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

	@Autowired
	private UsuarioResponseDtoAssembler assembler;
	
	@Autowired
	private UsuarioRequestDtoDesassembler desassembler;
	
	@Autowired
	private CadastroUsuarioService service;
	
	@GetMapping
	public CollectionModel<UsuarioResponseDTO> listar(){
		return assembler.toCollectionModel(service.listar()); 
	}
	
	@GetMapping("{id}")
	public UsuarioResponseDTO buscar(@PathVariable Long id){
		return assembler.toModel(service.buscar(id)); 
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public UsuarioResponseDTO criar(@Valid @RequestBody UsuarioComSenhaRequestDTO dto) {
		var usuario = desassembler.toModel(dto);
		return assembler.toModel(service.criar(usuario));
	}
	
	@PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public UsuarioResponseDTO alterar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO dto) {
		var usuario = service.buscar(id);
		desassembler.copyDtoToModel(dto, usuario);
		return assembler.toModel(service.alterar(usuario));
	}
	
	@PutMapping(value = "{id}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarSenha(@PathVariable Long id, @Valid @RequestBody SenhaRequestDTO dto) {
		var usuario = service.buscar(id);
		service.alterarSenha(usuario, dto.getSenhaAntiga(), dto.getSenhaNova());
	}
}
