package br.com.felipe.gorisfood.authorizationserver.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.jwk.JWKSet;

@RestController
public class jwkSetController {

//	@Autowired
	private JWKSet jwkSet;
	
	@GetMapping(".well-know/jwks.json")
	public Map<String, Object> keys(){
		return jwkSet.toJSONObject();
	}
}
