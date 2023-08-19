package br.com.felipe.gorisfood.authorizationserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	@GetMapping("/login")
	public String login() {
		return "pages/login";
	}
}
