package br.com.felipe.gorisfood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.felipe.gorisfood.core.validation.ValorZeroIncluiDescricao;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Restaurante {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;

	@ManyToOne
	@JoinColumn(name="cozinha_id", nullable = false)
	private Cozinha cozinha;

	@Embedded
	private Endereco endereco;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataAtualizacao;
	
	private Boolean ativo = Boolean.TRUE;
	
	private Boolean aberto = Boolean.FALSE;
	
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento",
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")
			
	)
	private Set<FormaPagamento> formasPagamento = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "restaurante_usuario_responsavel",
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "usuario_id")
			
	)
	private Set<Usuario> responsaveis = new HashSet<>();
	
	@OneToMany(mappedBy = "restaurante")
	private Set<Produto> produtos = new HashSet<>();

	
	public void ativar() {
		setAtivo(true);
	}
	
	public void inativar() {
		setAtivo(false);
	}
	
	public void abrir() {
		setAberto(true);
	}
	
	public void fechar() {
		setAberto(false);
	}
	
	public boolean isAberto() {
		return this.aberto;
	}
	
	public boolean isFechado() {
		return !isAberto();
	}
	
	public boolean isInativo() {
	    return !isAtivo();
	}

	public boolean isAtivo() {
	    return this.ativo;
	}
	
	public boolean aberturaPermitida() {
	    return isAtivo() && isFechado();
	}

	public boolean ativacaoPermitida() {
	    return isInativo();
	}

	public boolean inativacaoPermitida() {
	    return isAtivo();
	}

	public boolean fechamentoPermitido() {
	    return isAberto();
	}       
	
	public boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
		return formasPagamento.add(formaPagamento);
	}
	
	public boolean removerFormaPagamento(FormaPagamento formaPagamento) {
		return formasPagamento.remove(formaPagamento);
	}
	
	public boolean adicionarResponsavel(Usuario responsavel) {
		return getResponsaveis().add(responsavel);
	}
	
	public boolean removerResponsavel(Usuario responsavel) {
		return getResponsaveis().remove(responsavel);
	}
	
	public boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().contains(formaPagamento);
	}
	
	public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
		return !aceitaFormaPagamento(formaPagamento);
	}
	
	public boolean produtoPertenceAoRestaurante(Produto produto) {
		return getProdutos().contains(produto);
	}
	
	public boolean produtoNaoPertenceAoRestaurante(Produto produto) {
		return !produtoPertenceAoRestaurante(produto);
	}
}
