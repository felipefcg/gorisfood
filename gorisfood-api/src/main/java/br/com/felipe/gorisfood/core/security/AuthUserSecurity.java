package br.com.felipe.gorisfood.core.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.domain.repository.PedidoRepository;
import br.com.felipe.gorisfood.domain.repository.RestauranteRepository;

@Component
public class AuthUserSecurity {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public Long getUsuarioId() {
		var jwt = (Jwt) getAuthentication().getPrincipal();
		return jwt.getClaim("usuario_id");
	}
	
	public boolean gerenciaRestaurante(Long restauranteId) {
		
		if (restauranteId == null) {
	        return false;
	    }
		
		var existsResponsavel = restauranteRepository.existsResponsavel(restauranteId, getUsuarioId());
		return existsResponsavel;
	}
	
	public boolean gerenciaRestauranteDoPedido(String codigoPedido) {
		
		if (StringUtils.isBlank(codigoPedido)) {
	        return false;
	    }
		
		return pedidoRepository.isPedidoGerenciadoPor(codigoPedido, getUsuarioId());
		
	}
}
