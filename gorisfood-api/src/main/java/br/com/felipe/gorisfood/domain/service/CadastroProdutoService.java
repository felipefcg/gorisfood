package br.com.felipe.gorisfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<Produto> listar(Long restauranteId) {
		var restaurante = restauranteService.buscar(restauranteId);
		return produtoRepository.findByRestaurante(restaurante);
	}
	
	public Produto buscar(Long prodtuoId, Long restauranteId) {
		return produtoRepository.findByIdAndRestauranteId(prodtuoId, restauranteId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(prodtuoId, restauranteId));
	}
	
	@Transactional
	public Produto criar(Long restauranteId, Produto produto) {
		var restaurante = restauranteService.buscar(restauranteId);
		produto.setRestaurante(restaurante);
		return produtoRepository.save(produto);
	}

	@Transactional
	public Produto alterar(Produto produto) {
		return produtoRepository.save(produto);
	}
	
//	@Transactional
//	public void remover(Long id) {
//		try {
//			produtoRepository.deleteById(id);
//		} catch (EmptyResultDataAccessException e) {
//			throw new ProdutoNaoEncontradoException(id);
//		}
//		
//	}

}
