package br.com.felipe.gorisfood.domain.repository;

import java.util.List;

import br.com.felipe.gorisfood.domain.model.FormaPagamento;
import br.com.felipe.gorisfood.domain.model.Permissao;

public interface PermissaoRepository {
	List<Permissao> listar();
	Permissao buscar(Long id);
	Permissao salvar(Permissao permissao);
	void remover(Permissao permissao);
}
