package br.com.felipe.gorisfood.authorizationserver.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.felipe.gorisfood.authorizationserver.service.OAuth2AuthorizationQueryService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthorizedClientsController {

	private final OAuth2AuthorizationQueryService oAuth2AuthorizationQueryService;
	
	@GetMapping("oauth2/authorized-clients")
	public String clientsList(Principal principal, Model model) {
		List<RegisteredClient> clientsWithConsent = oAuth2AuthorizationQueryService.listClientsWithConsent(principal.getName());
		model.addAttribute("clients", clientsWithConsent);
		
		return "pages/authorized-clients";
	}
}
