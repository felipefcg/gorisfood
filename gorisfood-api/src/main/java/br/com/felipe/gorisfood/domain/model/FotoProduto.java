package br.com.felipe.gorisfood.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class FotoProduto {
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "produto_id")
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Produto produto;
	
	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;
	
	public Long getProdutoId() {
		if(getProduto() == null) {
			return null;
		}
		
		return getProduto().getId();
	}
	
	public Long getRestauranteId() {
		if(getProduto() == null || getProduto().getRestaurante() == null) {
			return null;
		}
		
		return getProduto().getRestaurante().getId();
	}
}
