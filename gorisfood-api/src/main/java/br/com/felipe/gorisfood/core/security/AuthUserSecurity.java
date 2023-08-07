package br.com.felipe.gorisfood.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.domain.repository.RestauranteRepository;

@Component
public class AuthUserSecurity {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public Long getUsuarioId() {
		var jwt = (Jwt) getAuthentication().getPrincipal();
		return jwt.getClaim("usuario_id");
	}
	
	public boolean gerenciaRestaurante(Long restauranteId) {
		var existsResponsavel = restauranteRepository.existsResponsavel(restauranteId, getUsuarioId());
		return existsResponsavel;
	}
}
