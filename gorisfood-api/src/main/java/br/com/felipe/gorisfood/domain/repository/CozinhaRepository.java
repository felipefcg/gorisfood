package br.com.felipe.gorisfood.domain.repository;

import org.springframework.stereotype.Repository;

import br.com.felipe.gorisfood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {
}
