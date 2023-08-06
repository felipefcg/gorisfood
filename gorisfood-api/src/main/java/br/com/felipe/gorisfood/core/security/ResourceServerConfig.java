package br.com.felipe.gorisfood.core.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig  {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
				.oauth2ResourceServer()
					.jwt()
					.jwtAuthenticationConverter(jwtAuthenticationConverter())
			;
		
		return http.build();
	}

	private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
		var jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(this::jwtGrantedAuthoritiesConverter);
				
		return jwtAuthenticationConverter;
	}

	private Collection<GrantedAuthority> jwtGrantedAuthoritiesConverter(Jwt jwt) {
		var authorities = jwt.getClaimAsStringList("authorities");
		
		if(authorities == null) {
			authorities = Collections.EMPTY_LIST;
		}
		
		List<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
				.map(SimpleGrantedAuthority::new)
				.toList();
		
		var scopeAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		Collection<GrantedAuthority> grantedAuthorities = scopeAuthoritiesConverter.convert(jwt);
		
		grantedAuthorities.addAll(simpleGrantedAuthorities);
		
		return grantedAuthorities;
	}

	
}
