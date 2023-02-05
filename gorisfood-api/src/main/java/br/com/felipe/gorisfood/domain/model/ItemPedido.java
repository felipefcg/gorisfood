package br.com.felipe.gorisfood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;
	private String observacao;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;
	
	public void calcularPrecoTotal() {
		
		var precoUnitario = this.getPrecoUnitario();
		var quantidade = this.getQuantidade();
				
		if(precoUnitario == null) {
			precoUnitario = BigDecimal.ZERO;
		}
		
		if(quantidade == null) {
			quantidade = 0;
		}
		
		this.setPrecoTotal(
				getPrecoUnitario().multiply(new BigDecimal(quantidade))
		);
		
	}
}
