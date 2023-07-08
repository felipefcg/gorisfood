package br.com.felipe.gorisfood.auth.config;

import java.util.ArrayList;

import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.http.UserDetailsServiceFactoryBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig {
	
	@Bean
	public InMemoryUserDetailsManager inMemoryUser() {
		var users = new ArrayList<UserDetails>();
		users.add(User.withDefaultPasswordEncoder()
				.username("felipe")
				.password("123")
				.roles("ADMIN")
				.build());
		
		users.add(User.withUsername("goris")
					.password(passwordEncoder().encode("123"))
					.roles("ADMIN")
					.build());
		return new InMemoryUserDetailsManager(users);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
//	@Bean
//	UserDetailsService userDetailsService(AuthenticationConfiguration s) {
//		return User;
//	}
}
