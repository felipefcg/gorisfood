package br.com.felipe.gorisfood.core.security;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig  {
	
	@Bean
	public SecurityFilterChain securityFilterChainV2(HttpSecurity http) throws Exception {
		http.httpBasic()
			.and()
			.authorizeHttpRequests()
				.antMatchers("/v1/cozinhas/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.csrf().disable();
		
		return http.build();
	}
	
	@Bean
	public InMemoryUserDetailsManager inMemoryUser() {
		var users = new ArrayList<UserDetails>();
		users.add(User.withDefaultPasswordEncoder()
				.username("felipe")
				.password("123")
				.roles("ADMIN")
				.build());
		
		users.add(User.withDefaultPasswordEncoder()
					.username("goris")
					.password("123")
					.roles("ADMIN")
					.build());
		return new InMemoryUserDetailsManager(users);
	}
	
}
