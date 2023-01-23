package br.com.felipe.gorisfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.felipe.gorisfood.domain.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {}
