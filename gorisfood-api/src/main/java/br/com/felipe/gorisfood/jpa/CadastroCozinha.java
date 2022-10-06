package br.com.felipe.gorisfood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transaction;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.domain.model.Cozinha;

@Component
public class CadastroCozinha {

	@PersistenceContext
	private EntityManager em;
	
	public List<Cozinha> listar() {
		return em.createQuery("from Cozinha", Cozinha.class).getResultList();
	}
	
	@Transactional
	public Cozinha inserir (Cozinha cozinha) {
		return em.merge(cozinha);
	}
}
