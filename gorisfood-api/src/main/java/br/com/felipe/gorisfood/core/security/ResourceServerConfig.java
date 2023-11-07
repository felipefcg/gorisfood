package br.com.felipe.gorisfood.core.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig {
	
	@Bean
	SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(Customizer.withDefaults())
			.csrf((csrf) -> csrf.disable())
			.cors(Customizer.withDefaults())
			.oauth2ResourceServer((oauth2ResourceServer) -> 
				oauth2ResourceServer.jwt((jwt) ->
				jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
			))
			;
		
		return http.build();
	}
	
	private JwtAuthenticationConverter jwtAuthenticationConverter() {
		var converter = new JwtAuthenticationConverter();
		
		converter.setJwtGrantedAuthoritiesConverter(jwt -> {
			List<String> authorities = jwt.getClaimAsStringList("authorities");
			
			if(authorities == null) {
				return Collections.emptyList();
			}
			var authoritiesConverter = new JwtGrantedAuthoritiesConverter();
			Collection<GrantedAuthority> grantedAuthorities = authoritiesConverter.convert(jwt);
			grantedAuthorities.addAll(authorities
					.stream()
					.map(SimpleGrantedAuthority::new)
					.toList());
			
			return grantedAuthorities;
		});
		
		return converter;
	}
	
}
