package br.com.felipe.gorisfood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.assembler.PermissaoResponseDtoAssembler;
import br.com.felipe.gorisfood.api.model.response.PermissaoResponseDTO;
import br.com.felipe.gorisfood.api.openapi.controller.PermissoesControllerOpenApi;
import br.com.felipe.gorisfood.domain.service.CadastroPermissaoService;

@RestController
@RequestMapping("permissoes")
public class PermissoesController implements PermissoesControllerOpenApi {

	@Autowired
	private PermissaoResponseDtoAssembler permissaoAssembler;
	
	@Autowired
	private CadastroPermissaoService permissaoService;
	
	@GetMapping
	public CollectionModel<PermissaoResponseDTO> listar() {
		return permissaoAssembler.toCollectionModel(permissaoService.buscar());
	}
}
