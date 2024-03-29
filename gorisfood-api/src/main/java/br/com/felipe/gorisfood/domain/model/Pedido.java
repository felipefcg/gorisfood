package br.com.felipe.gorisfood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import br.com.felipe.gorisfood.domain.event.PedidoCanceladoEvent;
import br.com.felipe.gorisfood.domain.event.PedidoConfirmadoEvent;
import br.com.felipe.gorisfood.domain.exception.EntidadeInconsistenteException;
import br.com.felipe.gorisfood.domain.model.enums.StatusPedido;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

@FieldNameConstants
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Pedido extends AbstractAggregateRoot<Pedido> {

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
	
	public boolean podeConfirmar() {
		return getStatus().podeAlterarPara(StatusPedido.CONFIRMADO);
	}
	
	public boolean podeCancelar() {
		return getStatus().podeAlterarPara(StatusPedido.CANCELADO);
	}
	
	public boolean podeEntregar() {
		return getStatus().podeAlterarPara(StatusPedido.ENTREGUE);
	}
	
	public void confirmar() {
		setStatus(StatusPedido.CONFIRMADO);
		setDataConfirmacao(OffsetDateTime.now());
		registerEvent(new PedidoConfirmadoEvent(this));
	}
	
	public void entregar() {
		setStatus(StatusPedido.ENTREGUE);
		setDataEntrega(OffsetDateTime.now());
	}
	
	public void cancelar() {
		setStatus(StatusPedido.CANCELADO);
		setDataCancelamento(OffsetDateTime.now());
		registerEvent(new PedidoCanceladoEvent(this));
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
