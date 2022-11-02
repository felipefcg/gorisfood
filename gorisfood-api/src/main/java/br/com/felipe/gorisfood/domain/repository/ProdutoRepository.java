package br.com.felipe.gorisfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.felipe.gorisfood.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
