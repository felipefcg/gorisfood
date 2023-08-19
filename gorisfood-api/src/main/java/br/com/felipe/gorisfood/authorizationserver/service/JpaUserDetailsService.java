package br.com.felipe.gorisfood.authorizationserver.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.authorizationserver.model.AuthUser;
import br.com.felipe.gorisfood.domain.model.Usuario;
import br.com.felipe.gorisfood.domain.repository.UsuarioRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var usuario = usuarioRepository.findByEmail(username)
			.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com e-mail informado."));
		
		return new AuthUser(usuario, getAuthorities(usuario));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Usuario usuario) {
		
		return usuario.getGrupos().stream()
				.flatMap( grupo -> grupo.getPermissoes().stream())
				.map( permissao -> new SimpleGrantedAuthority(permissao.getNome().toUpperCase()))
				.collect(Collectors.toSet())
		;
	}
	

}
