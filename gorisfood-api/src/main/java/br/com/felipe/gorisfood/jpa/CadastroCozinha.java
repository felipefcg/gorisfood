package br.com.felipe.gorisfood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.domain.model.Cozinha;

@Component
public class CadastroCozinha {

	@PersistenceContext
	private EntityManager em;
	
	public List<Cozinha> listar() {
		return em.createQuery("from Cozinha", Cozinha.class).getResultList();
	}
}
