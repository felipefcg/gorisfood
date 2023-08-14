package br.com.felipe.gorisfood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.assembler.PermissaoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.v1.model.response.PermissaoResponseDTO;
import br.com.felipe.gorisfood.api.v1.openapi.controller.GrupoPermissaoControllerOpenApi;
import br.com.felipe.gorisfood.core.security.CheckSecurity;
import br.com.felipe.gorisfood.domain.service.CadastroGupoService;

@RestController
@RequestMapping(value =  "v1/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

	@Autowired
	private CadastroGupoService grupoService; 
	
	@Autowired
	private PermissaoResponseDtoAssembler permissaoAssembler;
	
	@Autowired
	private GorisLinks gorisLinks;
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping
	public CollectionModel<PermissaoResponseDTO> listar(@PathVariable Long grupoId) {
		var grupo = grupoService.buscar(grupoId);
		var permissoes = grupo.getPermissoes();
		
		CollectionModel<PermissaoResponseDTO> collectionModel = permissaoAssembler.toCollectionModel(permissoes)
			.removeLinks()
			.add(gorisLinks.linkToGrupoPermissaoAssociar(grupoId, "associar"))
			.add(gorisLinks.linkToGrupoPermissoes(grupoId));
		
		collectionModel.getContent()
			.forEach(p -> p.add(gorisLinks.linkToGrupoPermissaoDesassociar(grupoId, p.getId(), "desassociar")));
		
		return collectionModel;
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@PutMapping("{permissaoId}")
	public ResponseEntity<Void> associar (@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.adicionarPermissao(grupoId, permissaoId);
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@DeleteMapping("{permissaoId}")
	public ResponseEntity<Void> desassociar (@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.removerPermissao(grupoId, permissaoId);
		return ResponseEntity.noContent().build();
	}
}
