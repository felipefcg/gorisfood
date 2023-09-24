package br.com.felipe.gorisfood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.core.security.AuthUserSecurity;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntyPointController {

	@Autowired
	private GorisLinks gorisLinks;
	
	@Autowired
	private AuthUserSecurity authUserSecurity;

	@Operation(hidden = true)
	@GetMapping
	public RootEntryPointResponseDTO root() {
		var root = new RootEntryPointResponseDTO();
		
		if(authUserSecurity.podeConsultarCozinhas()) {
			root.add(gorisLinks.linkToCozinhas("cozinhas"));
		}
		
		if(authUserSecurity.podePesquisarPedidos()) {
			root.add(gorisLinks.linkToPedidos("pedidos"));
		}
		
		if(authUserSecurity.podeConsultarRestaurantes()) {
			root.add(gorisLinks.linkToRestaurantes("restaurantes"));
		}
		
		if(authUserSecurity.podeConsultarUsuariosGruposPermissoes()) {
			root.add(gorisLinks.linkToGrupos("grupos"));
			root.add(gorisLinks.linkToUsuarios("usuarios"));
			root.add(gorisLinks.linkToPermissoes("permissoes"));
		}
		
		if(authUserSecurity.podeConsultarFormasPagamento()) {
			root.add(gorisLinks.linkToFormasPagamento("formas-pagamento"));
		}
		
		if(authUserSecurity.podeConsultarEstados()) {
			root.add(gorisLinks.linkToEstados("estados"));
		}
		
		if(authUserSecurity.podeConsultarCidades()) {
			root.add(gorisLinks.linkToCidades("cidades"));
		}
		
		if(authUserSecurity.podeConsultarEstatisticas()) {
			root.add(gorisLinks.linkToEstatiticas("estatisticas"));
		}
		
		return root;
	}
	
	private class RootEntryPointResponseDTO 
		extends RepresentationModel<RootEntryPointResponseDTO>{
		
	}
}
