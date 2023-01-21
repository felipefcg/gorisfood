package br.com.felipe.gorisfood.domain.repository;

import java.util.Optional;

import br.com.felipe.gorisfood.domain.model.Usuario;

public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);
}
