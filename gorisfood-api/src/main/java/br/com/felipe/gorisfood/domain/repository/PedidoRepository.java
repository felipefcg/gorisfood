package br.com.felipe.gorisfood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.felipe.gorisfood.domain.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
		@Query("from Pedido p "
				+ " join fetch p.cliente"
				+ " join fetch p.restaurante"
				+ " join fetch p.restaurante.cozinha"
				+ " join fetch p.enderecoEntrega.cidade"
				+ " join fetch p.enderecoEntrega.cidade.estado")
		List<Pedido> findAll();
}
