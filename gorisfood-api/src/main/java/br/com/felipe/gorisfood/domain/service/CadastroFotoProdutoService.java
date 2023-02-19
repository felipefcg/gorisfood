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
	public FotoProduto salvar(FotoProduto foto) {

		var produto = cadastroProtudoService.buscar(foto.getProdutoId(), foto.getRestauranteId());
		foto.setProduto(produto);
		
		var fotoProdutoExistente = repository.findFotoById(foto.getProdutoId(), foto.getRestauranteId());
		if(fotoProdutoExistente.isPresent()) {
			repository.delete(fotoProdutoExistente.get());
		}
		
		return repository.save(foto);
	}
}
