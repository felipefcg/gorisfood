package br.com.felipe.gorisfood.domain.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.domain.exception.ProdutoNaoEncontradoException;
import br.com.felipe.gorisfood.domain.model.Produto;
import br.com.felipe.gorisfood.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CadastroRestauranteService restauranteService;
	
	public Set<Produto> listar(Long restauranteId, boolean incluirInativos) {
		var restaurante = restauranteService.buscar(restauranteId);
		
		if (incluirInativos) {
			return restaurante.getProdutos();
		}
		
		return produtoRepository.findAtivosByRestaurante(restaurante);
	}
	
	public Produto buscar(Long prodtuoId, Long restauranteId) {

		//OPÇÃO 1
		return produtoRepository.findByIdAndRestauranteId(prodtuoId, restauranteId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(prodtuoId, restauranteId));
		
		//OPÇÃO 2
//		var restaurante = restauranteService.buscar(restauranteId);
//		return restaurante.getProdutos()
//					.stream()
//					.filter(p -> p.getId().equals(prodtuoId))
//					.findFirst()
//					.orElseThrow(() -> new ProdutoNaoEncontradoException(prodtuoId, restauranteId));
			
			
		
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
	
	@Transactional
	public void remover(Long produtoId, Long restauranteId) {
		if (produtoRepository.deleteByIdAndRestauranteId(produtoId, restauranteId)==0) {
			throw new ProdutoNaoEncontradoException(produtoId, restauranteId);
		}
	}

}
