package br.com.felipe.gorisfood.api.v1.controller;

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

import br.com.felipe.gorisfood.api.v1.assembler.UsuarioRequestDtoDesassembler;
import br.com.felipe.gorisfood.api.v1.assembler.UsuarioResponseDtoAssembler;
import br.com.felipe.gorisfood.api.v1.model.request.SenhaRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.request.UsuarioComSenhaRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.request.UsuarioRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.UsuarioResponseDTO;
import br.com.felipe.gorisfood.api.v1.openapi.controller.UsuarioControllerOpenApi;
import br.com.felipe.gorisfood.core.security.CheckSecurity;
import br.com.felipe.gorisfood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(value =  "v1/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

	@Autowired
	private UsuarioResponseDtoAssembler assembler;
	
	@Autowired
	private UsuarioRequestDtoDesassembler desassembler;
	
	@Autowired
	private CadastroUsuarioService service;
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping
	public CollectionModel<UsuarioResponseDTO> listar(){
		return assembler.toCollectionModel(service.listar()); 
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping("{id}")
	public UsuarioResponseDTO buscar(@PathVariable Long id){
		return assembler.toModel(service.buscar(id)); 
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public UsuarioResponseDTO criar(@Valid @RequestBody UsuarioComSenhaRequestDTO dto) {
		var usuario = desassembler.toModel(dto);
		return assembler.toModel(service.criar(usuario));
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeAlterarUsuario
	@PutMapping(value = "{usuarioId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public UsuarioResponseDTO alterar(@PathVariable Long usuarioId, @Valid @RequestBody UsuarioRequestDTO dto) {
		var usuario = service.buscar(usuarioId);
		desassembler.copyDtoToModel(dto, usuario);
		return assembler.toModel(service.alterar(usuario));
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeAlterarPropriaSenha
	@PutMapping(value = "{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarSenha(@PathVariable Long usuarioId, @Valid @RequestBody SenhaRequestDTO dto) {
		var usuario = service.buscar(usuarioId);
		service.alterarSenha(usuario, dto.getSenhaAntiga(), dto.getSenhaNova());
	}
}
