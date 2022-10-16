package br.com.felipe.gorisfood.domain.repository;

import java.util.List;

import br.com.felipe.gorisfood.domain.model.Cozinha;

public interface CozinhaRepository {
	List<Cozinha> listar();
	Cozinha buscar(Long id);
	Cozinha salvar(Cozinha cozinha);
	void remover(Long id);
	void remover(Cozinha cozinha);
}
