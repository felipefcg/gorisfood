package br.com.felipe.gorisfood.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig  {
	
	@Bean
	public SecurityFilterChain securityFilterChainV2(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests()
				.anyRequest().authenticated()
			.and()
				.oauth2ResourceServer().opaqueToken()
			;
		
		return http.build();
	}
	
}
