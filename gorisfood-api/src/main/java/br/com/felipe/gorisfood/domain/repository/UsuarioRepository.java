package br.com.felipe.gorisfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.felipe.gorisfood.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
