package br.com.felipe.gorisfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import br.com.felipe.gorisfood.domain.model.Restaurante;

public interface RestauranteRepositoryCustom {

	List<Restaurante> consultarPorCozinhaETaxa(String nomeCozinha, BigDecimal taxaInicial, BigDecimal taxaFinal);
	List<Restaurante> consultarFreteGratis(String nome);
}