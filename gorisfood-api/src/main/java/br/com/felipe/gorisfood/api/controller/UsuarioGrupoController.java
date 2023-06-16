package br.com.felipe.gorisfood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.GorisLinks;
import br.com.felipe.gorisfood.api.assembler.GrupoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.model.response.GrupoResponseDTO;
import br.com.felipe.gorisfood.api.openapi.controller.UsuarioGrupoControllerOpenApi;
import br.com.felipe.gorisfood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(value = "usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

	@Autowired
	private CadastroUsuarioService usuarioService;
	
	@Autowired
	private GrupoResponseDtoAssembler grupoAssembler;
	
	@Autowired
	private GorisLinks gorisLinks;
	
	@GetMapping
	public CollectionModel<GrupoResponseDTO> listar(@PathVariable Long usuarioId){
		var usuario = usuarioService.buscar(usuarioId);
		return grupoAssembler.toCollectionModel(usuario.getGrupos())
				.removeLinks()
				.add(gorisLinks.linkToGruposUsuario(usuarioId)); 
	}
	
	@PutMapping("{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void adicionar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioService.adicionarGrupo(usuarioId, grupoId);
	}
	
	@DeleteMapping("{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioService.removerGrupo(usuarioId, grupoId);
	}
}
