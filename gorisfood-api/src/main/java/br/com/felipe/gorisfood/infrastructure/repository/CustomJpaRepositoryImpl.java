package br.com.felipe.gorisfood.infrastructure.repository;

import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import br.com.felipe.gorisfood.domain.repository.CustomJpaRepository;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {

	private EntityManager em;
	
	public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
	}

	public Optional<T> buscarPrimeiro(){
		var jpql = "from " + getDomainClass().getName();
		T result;
		
		try {
			result = em.createQuery(jpql, getDomainClass())
				.setMaxResults(1)
				.getSingleResult();
		} catch (NoResultException e) {
			result = null;
		}
		return Optional.ofNullable(result);
	}

	@Override
	public void detached(T entity) {
		em.detach(entity);
	}
	
}
