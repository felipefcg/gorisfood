package br.com.felipe.gorisfood.auth.core.model;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;

import br.com.felipe.gorisfood.auth.domain.model.Usuario;
import lombok.Getter;

@Getter
public class AuthUser extends User {

	private static final long serialVersionUID = 1L;
	private String fullName; 
	
	public AuthUser(Usuario usuario) {
		super(usuario.getEmail(), usuario.getSenha(), Collections.EMPTY_LIST);
		this.fullName = usuario.getNome();
	}

}
