package br.com.felipe.gorisfood.domain.repository;

import br.com.felipe.gorisfood.domain.model.FotoProduto;

public interface ProdutoQueryRepository {
	FotoProduto save(FotoProduto fotoProduto);
	void delete(FotoProduto fotoProduto);
}
