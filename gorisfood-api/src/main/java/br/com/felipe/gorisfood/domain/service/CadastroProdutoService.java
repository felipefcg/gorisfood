package br.com.felipe.gorisfood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.domain.exception.ProdutoNaoEncontradoException;
import br.com.felipe.gorisfood.domain.model.Produto;
import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CadastroRestauranteService restauranteService;

	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	public Produto buscar(Long id) {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(id));
	}
	
	@Transactional
	public Produto criar(Produto novoProduto) {
		inserirRestaurante(novoProduto);
		return produtoRepository.save(novoProduto);
	}

	@Transactional
	public Produto alterar(Long id, Produto produtoAlterado) {
		Produto produtoSalvo = buscar(id);
		inserirRestaurante(produtoAlterado);
		BeanUtils.copyProperties(produtoAlterado, produtoSalvo, "id", "restaurante");
		return produtoRepository.save(produtoSalvo);
	}
	
	@Transactional
	public void remover(Long id) {
		try {
			produtoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(id);
		}
		
	}
	
	private void inserirRestaurante(Produto produto) {
		Long idRestaurante = produto.getRestaurante().getId();
		Restaurante restaurante = restauranteService.buscar(idRestaurante);
		produto.setRestaurante(restaurante);
	}


}
