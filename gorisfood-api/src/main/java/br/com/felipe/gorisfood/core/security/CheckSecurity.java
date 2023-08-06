package br.com.felipe.gorisfood.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface Cozinha {
		
		@PreAuthorize("isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}
		
		@PreAuthorize("hasAuthority('EDITAR_COZINHAS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar {}
	}
}
