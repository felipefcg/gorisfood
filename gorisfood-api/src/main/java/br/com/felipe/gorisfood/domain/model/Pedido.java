package br.com.felipe.gorisfood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import org.hibernate.annotations.CreationTimestamp;

import br.com.felipe.gorisfood.domain.exception.EntidadeInconsistenteException;
import br.com.felipe.gorisfood.domain.model.enums.StatusPedido;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codigo;
	
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status = StatusPedido.CRIADO;
	
	@Embedded
	private Endereco enderecoEntrega;
	
	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario cliente;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens;
	
	@CreationTimestamp
	@Column(columnDefinition = "datetime")
	private OffsetDateTime dataCriacao;
	
	@Column(columnDefinition = "datetime")
	private OffsetDateTime dataConfirmacao;
	
	@Column(columnDefinition = "datetime")
	private OffsetDateTime dataCancelamento;
	
	@Column(columnDefinition = "datetime")
	private OffsetDateTime dataEntrega;
	
	private void setStatus(StatusPedido novoStatus) {
		
		if(getStatus().naoPodeAlterarPara(novoStatus)) {
			throw new EntidadeInconsistenteException(String.format("Status do pedido %s não pode ser alterado de %s para %s", 
					getCodigo(), getStatus().getDescricao(), novoStatus.getDescricao()));
		}
		
		this.status = novoStatus;
	}
	
	public void confirmar() {
		setStatus(StatusPedido.CONFIRMADO);
		setDataConfirmacao(OffsetDateTime.now());
	}
	
	public void entregar() {
		setStatus(StatusPedido.ENTREGUE);
		setDataEntrega(OffsetDateTime.now());
	}
	
	public void cancelar() {
		setStatus(StatusPedido.CANCELADO);
		setDataCancelamento(OffsetDateTime.now());
	}
	
	public void calculaValorTotal() {
		getItens().forEach(ItemPedido::calcularPrecoTotal);
		
		//frete +  itens
		subtotal = getItens().stream()
					.map(ItemPedido::getPrecoTotal)
					.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		setValorTotal(subtotal.add(taxaFrete));
	}
	
	@PrePersist
	private void gerarCodigo() {
		setCodigo(UUID.randomUUID().toString());
	}
}
