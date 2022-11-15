package br.com.felipe.gorisfood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.felipe.gorisfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.EntidadeRelacionamentoNaoEncontradaException;
import br.com.felipe.gorisfood.domain.model.Produto;
import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.repository.ProdutoRepository;
import br.com.felipe.gorisfood.domain.repository.RestauranteRepository;

@Service
public class CadastroProdutoService {

	private static final String NAO_EXISTE_RESTAURANTE = "Não foi encontrado restaurante com id %d";

	private static final String MSG_NAO_EXISTE_PRODUTO = "Não existe um cadastro de produto com o código %d.";

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;

	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	public Produto buscar(Long id) {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(MSG_NAO_EXISTE_PRODUTO, id)));
	}

	public Produto criar(Produto novoProduto) {
		inserirRestaurante(novoProduto);
		return produtoRepository.save(novoProduto);
	}

	public Produto alterar(Long id, Produto produtoAlterado) {
		Produto produtoSalvo = buscar(id);
		inserirRestaurante(produtoAlterado);
		BeanUtils.copyProperties(produtoAlterado, produtoSalvo, "id", "restaurante");
		return produtoRepository.save(produtoSalvo);
	}
	
	public void remover(Long id) {
		try {
			produtoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_NAO_EXISTE_PRODUTO, id));
		}
		
	}
	
	private void inserirRestaurante(Produto produto) {
		Long idRestaurante = produto.getRestaurante().getId();
		Restaurante restaurante = restauranteRepository.findById(idRestaurante)
			.orElseThrow(() -> new EntidadeRelacionamentoNaoEncontradaException(
					String.format(NAO_EXISTE_RESTAURANTE , idRestaurante)));
		produto.setRestaurante(restaurante);
		
	}


}
