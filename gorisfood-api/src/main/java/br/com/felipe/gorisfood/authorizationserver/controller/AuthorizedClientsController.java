package br.com.felipe.gorisfood.authorizationserver.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.felipe.gorisfood.authorizationserver.service.OAuth2AuthorizationQueryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("oauth2/authorized-clients")
@RequiredArgsConstructor
public class AuthorizedClientsController {

	private final OAuth2AuthorizationQueryService oAuth2AuthorizationQueryService;
	private final RegisteredClientRepository clientRepository;
	private final OAuth2AuthorizationConsentService auth2AuthorizationConsentService;
	private final OAuth2AuthorizationService oAuth2AuthorizationService;
	
	@GetMapping
	public String clientsList(Principal principal, Model model, HttpServletRequest request) {
		request.getAttributeNames().asIterator().forEachRemaining(System.out::println);
		request.getHeaderNames().asIterator().forEachRemaining(System.out::println);
		List<RegisteredClient> clientsWithConsent = oAuth2AuthorizationQueryService.listClientsWithConsent(principal.getName());
		model.addAttribute("clients", clientsWithConsent);
		
		return "pages/authorized-clients";
	}
	
	@PostMapping("revoke")
	public String revoke(Principal principal, Model model, @RequestParam(OAuth2ParameterNames.CLIENT_ID) String clientId) {
		RegisteredClient registeredClient = this.clientRepository.findByClientId(clientId);
		
		if(registeredClient == null) {
			throw new AccessDeniedException(String.format("Cliente %s não encontrado", clientId));
		}
		
		OAuth2AuthorizationConsent consent = this.auth2AuthorizationConsentService.findById(registeredClient.getId(), principal.getName());
		
		List<OAuth2Authorization> authorizations = this.oAuth2AuthorizationQueryService.listAuthorizations(principal.getName(), registeredClient.getId());
		
		if(consent != null) {
			this.auth2AuthorizationConsentService.remove(consent);
		}
		
		for (OAuth2Authorization authorization : authorizations) {
			this.oAuth2AuthorizationService.remove(authorization);
		}
		return "redirect:/oauth2/authorized-clients";
	}
}
