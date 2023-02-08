package br.com.felipe.gorisfood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.felipe.gorisfood.domain.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
		
	Optional<Pedido> findByCodigo(String codigo);
	
	@Query("from Pedido p "
			+ " join fetch p.cliente c"
			+ " join fetch p.restaurante r"
			+ " join fetch r.cozinha"
			+ " join fetch p.enderecoEntrega e"
			+ " join fetch e.cidade ci"
			+ " join fetch ci.estado")
	List<Pedido> findAll();
}
