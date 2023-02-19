package br.com.felipe.gorisfood.domain.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.felipe.gorisfood.domain.model.FotoProduto;
import br.com.felipe.gorisfood.domain.model.Produto;
import br.com.felipe.gorisfood.domain.model.Restaurante;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoQueryRepository {
	Set<Produto> findByRestaurante(Restaurante restaurante);
	Optional<Produto> findByIdAndRestauranteId(Long id, Long restauranteId);
	Integer deleteByIdAndRestauranteId(Long id, Long restauranteId);
	
	@Query("from Produto p where p.ativo = true and p.restaurante = :restaurante")
	Set<Produto> findAtivosByRestaurante(Restaurante restaurante);
	
	@Query("""
            select fp
            from FotoProduto fp join fp.produto p  
            where fp.id = :produtoId
               and p.restaurante.id = :restauranteId
           """)
	Optional<FotoProduto> findFotoById(Long restauranteId, Long produtoId);
}
