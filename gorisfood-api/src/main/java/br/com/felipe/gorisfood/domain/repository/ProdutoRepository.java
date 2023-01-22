package br.com.felipe.gorisfood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.felipe.gorisfood.domain.model.Produto;
import br.com.felipe.gorisfood.domain.model.Restaurante;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	List<Produto> findByRestaurante(Restaurante restaurante);
	Optional<Produto> findByIdAndRestauranteId(Long id, Long restauranteId);
}
