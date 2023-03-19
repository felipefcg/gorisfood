package br.com.felipe.gorisfood.domain.repository;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.felipe.gorisfood.domain.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
	
	@Query("select max(dataAtualizacao) from FormaPagamento")
	Optional<OffsetDateTime> getDataUltimaAtualizacao();
	
	@Query("select dataAtualizacao from FormaPagamento where id = :formaPagamentoId")
	Optional<OffsetDateTime> getDataAtualizacaoById(Long formaPagamentoId);
}
