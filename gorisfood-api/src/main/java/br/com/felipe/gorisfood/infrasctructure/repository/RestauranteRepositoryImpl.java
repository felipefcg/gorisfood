package br.com.felipe.gorisfood.infrasctructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

	@PersistenceContext
	private final EntityManager em;
	
	@Override
	public List<Restaurante> listar() {
		return em.createQuery("from Restaurante", Restaurante.class).getResultList();
	}

	@Override
	public Restaurante buscar(Long id) {
		return em.find(Restaurante.class, id);
	}

	@Transactional
	@Override
	public Restaurante salvar(Restaurante restaurante) {
		return em.merge(restaurante);
	}

	@Transactional
	@Override
	public void remover(Restaurante restaurante) {
		restaurante = this.buscar(restaurante.getId());
		em.remove(restaurante);
	}

}
