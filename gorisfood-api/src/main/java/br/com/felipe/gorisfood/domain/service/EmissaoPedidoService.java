package br.com.felipe.gorisfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.domain.exception.CidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.EntidadeInconsistenteException;
import br.com.felipe.gorisfood.domain.exception.EntidadeRelacionamentoNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.FormaPagamentoNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.PedidoNaoEncontradoException;
import br.com.felipe.gorisfood.domain.exception.ProdutoNaoEncontradoException;
import br.com.felipe.gorisfood.domain.exception.RestauranteNaoEncontradoException;
import br.com.felipe.gorisfood.domain.exception.UsuarioNaoEncontradoException;
import br.com.felipe.gorisfood.domain.model.Pedido;
import br.com.felipe.gorisfood.domain.repository.PedidoRepository;

@Service
public class EmissaoPedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private CadastroRestauranteService restauranteService;
	
	@Autowired
	private CadastroCidadeService cidadeService;

	@Autowired
	private CadastroFormaPagamentoService formaPagamentoService;
	
	@Autowired
	private CadastroProdutoService produtoService;
	
	@Autowired
	private CadastroUsuarioService usuarioService;
	
	public List<Pedido> listar(){
		return pedidoRepository.findAll();
	}
	
	public Pedido buscar(String codigoPedido) {
		return pedidoRepository.findByCodigo(codigoPedido)
				.orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedido));
	}

	@Transactional
	public Pedido emitir(Pedido pedido) {
		
		validarPedido(pedido);
		validarItens(pedido);
		pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
		pedido.calculaValorTotal();
			
		return pedidoRepository.save(pedido);
		 
	}
	
	private void validarPedido(Pedido pedido) {
		try  {
			var restaurante = restauranteService.buscar(pedido.getRestaurante().getId());
			var formaPagamento = formaPagamentoService.buscar(pedido.getFormaPagamento().getId());
			var cidade = cidadeService.buscar(pedido.getEnderecoEntrega().getCidadeId()); 
			var cliente = usuarioService.buscar(pedido.getCliente().getId());
			
			if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
				throw new EntidadeInconsistenteException(
						String.format("Forma de pagamento '%s' não é aceita por esse restaurante.", 
								formaPagamento.getDescricao()));
			}
			
			pedido.setRestaurante(restaurante);
			pedido.setFormaPagamento(formaPagamento);
			pedido.setCliente(cliente);
			pedido.getEnderecoEntrega().setCidade(cidade);
			
		} catch (RestauranteNaoEncontradoException | FormaPagamentoNaoEncontradaException | 
				 CidadeNaoEncontradaException	   | UsuarioNaoEncontradoException e) {
			throw new EntidadeRelacionamentoNaoEncontradaException(e.getMessage());
		} 
		
	}
	
	private void validarItens(Pedido pedido) {
		try {
			var itensPedido = pedido.getItens();
			itensPedido.forEach(itemPedido -> {
				var produto = produtoService.buscar(itemPedido.getProduto().getId(), pedido.getRestaurante().getId());
				
				itemPedido.setPedido(pedido);
				itemPedido.setProduto(produto);
				itemPedido.setPrecoUnitario(produto.getPreco());
			});
		} catch (ProdutoNaoEncontradoException e) {
			throw new EntidadeRelacionamentoNaoEncontradaException(e.getMessage());
		}
	}
	
}
