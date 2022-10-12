package br.com.felipe.gorisfood.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ManyToAny;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Cozinha {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@OneToMany(mappedBy = "cozinha", fetch = FetchType.EAGER)
	private List<Restaurante> restaurantes;
}
