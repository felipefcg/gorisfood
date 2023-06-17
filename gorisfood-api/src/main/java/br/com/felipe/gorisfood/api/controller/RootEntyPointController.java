package br.com.felipe.gorisfood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.GorisLinks;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntyPointController {

	@Autowired
	private GorisLinks gorisLinks;
	@GetMapping
	public RootEntryPointResponseDTO root() {
		var root = new RootEntryPointResponseDTO();
		
		root.add(gorisLinks.linkToCozinhas("cozinhas"));
		root.add(gorisLinks.linkToPedidos("pedidos"));
		root.add(gorisLinks.linkToRestaurantes("restaurantes"));
		root.add(gorisLinks.linkToGrupos("grupos"));
		root.add(gorisLinks.linkToUsuarios("usuarios"));
		root.add(gorisLinks.linkToPermissoes("permissoes"));
		root.add(gorisLinks.linkToFormasPagamento("formas-pagamento"));
		root.add(gorisLinks.linkToEstados("estados"));
		root.add(gorisLinks.linkToCidades("cidades"));
		root.add(gorisLinks.linkToEstatiticas("estatisticas"));
		
		return root;
	}
	
	private class RootEntryPointResponseDTO 
		extends RepresentationModel<RootEntryPointResponseDTO>{
		
	}
}
