package br.com.felipe.gorisfood.api.v1.controller;

import jakarta.validation.Valid;

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

import br.com.felipe.gorisfood.api.v1.assembler.GrupoRequestDtoDesassembler;
import br.com.felipe.gorisfood.api.v1.assembler.GrupoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.v1.model.request.GrupoRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.GrupoResponseDTO;
import br.com.felipe.gorisfood.api.v1.openapi.controller.GrupoControllerOpenApi;
import br.com.felipe.gorisfood.core.security.CheckSecurity;
import br.com.felipe.gorisfood.domain.service.CadastroGupoService;

@RestController
@RequestMapping(value =  "v1/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

	@Autowired
	private CadastroGupoService service;
	
	@Autowired
	private GrupoRequestDtoDesassembler desassembler;
	
	@Autowired
	private GrupoResponseDtoAssembler assembler;
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping
	public CollectionModel<GrupoResponseDTO> listar(){
		return assembler.toCollectionModel(service.listar());
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping("{id}")
	public GrupoResponseDTO buscar(@PathVariable Long id) {
		return assembler.toModel(service.buscar(id));
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		service.excluir(id);
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoResponseDTO criar(@RequestBody @Valid GrupoRequestDTO dto) {
		var grupo = desassembler.toModel(dto);
		return assembler.toModel(service.salvar(grupo));
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public GrupoResponseDTO alterar(@PathVariable Long id, @RequestBody @Valid GrupoRequestDTO dto) {
		var grupo = service.buscar(id);
		desassembler.copyDtotoModel(dto, grupo);
		return assembler.toModel(service.salvar(grupo));
	}
}
