package br.com.felipe.gorisfood.domain.repository;

import java.util.List;

import br.com.felipe.gorisfood.domain.model.Estado;
import br.com.felipe.gorisfood.domain.model.Permissao;

public interface EstadoRepository {
	List<Estado> listar();
	Estado buscar(Long id);
	Estado salvar(Estado estado);
	void remover(Estado estado);
}
