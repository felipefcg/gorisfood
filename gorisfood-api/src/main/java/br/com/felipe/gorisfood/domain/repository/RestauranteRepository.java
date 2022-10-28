package br.com.felipe.gorisfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.infrastructure.repository.RestauranteRepositoryCustom;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryCustom {
	
	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<Restaurante> consultaPorNome(String nome, @Param("id") Long cozinha);
	
	List<Restaurante> consultaPorNomeETaxaFrete(String nome, BigDecimal taxaInicio, BigDecimal taxaFim);
}
