package br.com.felipe.gorisfood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipe.gorisfood.domain.model.FotoProduto;
import br.com.felipe.gorisfood.domain.repository.ProdutoRepository;

@Service
public class CadastroFotoProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CadastroProdutoService cadastroProtudoService;
	
	@Transactional
	public FotoProduto salvar(FotoProduto fotoProduto) {
		
		var produto = fotoProduto.getProduto();
		var restaurante= produto.getRestaurante();
		
		produto = cadastroProtudoService.buscar(produto.getId(), restaurante.getId());
		fotoProduto.setProduto(produto);
		return repository.salvarFotoProduto(fotoProduto);
	}
}
