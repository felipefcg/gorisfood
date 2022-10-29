package br.com.felipe.gorisfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.felipe.gorisfood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends RestauranteRepositoryCustom, 
												JpaRepository<Restaurante, Long>, 
												JpaSpecificationExecutor<Restaurante>  {
	
	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<Restaurante> consultaPorNome(String nome, @Param("id") Long cozinha);
	
	List<Restaurante> consultaPorNomeETaxaFrete(String nome, BigDecimal taxaInicio, BigDecimal taxaFim);
}
