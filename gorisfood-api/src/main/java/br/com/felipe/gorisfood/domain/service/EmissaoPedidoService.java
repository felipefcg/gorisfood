package br.com.felipe.gorisfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.domain.exception.CidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.EntidadeInconsistenteException;
import br.com.felipe.gorisfood.domain.exception.EntidadeRelacionamentoNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.PedidoNaoEncontradoException;
import br.com.felipe.gorisfood.domain.exception.RestauranteNaoEncontradoException;
import br.com.felipe.gorisfood.domain.model.Pedido;
import br.com.felipe.gorisfood.domain.model.Usuario;
import br.com.felipe.gorisfood.domain.model.enums.StatusPedido;
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
	private CadastroUsuarioService usuarioService;
	
	public List<Pedido> listar(){
		return pedidoRepository.findAll();
	}
	
	public Pedido buscar(Long id) {
		return pedidoRepository.findById(id)
				.orElseThrow(() -> new PedidoNaoEncontradoException(id));
	}

	@Transactional
	public Pedido salvar(Pedido pedido) {
		
		try {
			var restaurante = restauranteService.buscar(pedido.getRestaurante().getId());
			var formaPagamentoPedido = pedido.getFormaPagamento();
			
			var formaPagamentoRestaurante = restaurante.getFormasPagamento()
				.stream()
				.filter( fp -> fp.equals(formaPagamentoPedido))
				.findFirst()
				.orElseThrow(() -> 
					new EntidadeInconsistenteException(
						String.format("A forma de pagamento de codigo %s não é aceita pelo restaurante de código %d", 
								formaPagamentoPedido.getId(), restaurante.getId()))
				);
			
			var cidade = cidadeService.buscar(pedido.getEnderecoEntrega().getCidadeId());
			
			var itensPedido = pedido.getItens();
			itensPedido.forEach(itemPedido -> {
				var produtoPedido = itemPedido.getProduto();
				var produtoRestaurante = restaurante
						.getProdutos().stream()
						.filter(p-> p.equals(produtoPedido))
						.findFirst()
						.orElseThrow(() ->
							new EntidadeInconsistenteException(String.format("O produto código %d não pertence ao restaurante código %d", 
									produtoPedido.getId(), restaurante.getId()))
						);
				
				itemPedido.setProduto(produtoRestaurante);
				itemPedido.setPrecoUnitario(produtoRestaurante.getPreco());
				itemPedido.calcularPrecoTotal();
			});
			
			pedido.setRestaurante(restaurante);
			pedido.setFormaPagamento(formaPagamentoRestaurante);
			pedido.setCliente(getCliente());
			pedido.getEnderecoEntrega().setCidade(cidade);
			pedido.definirFrete(restaurante);
			pedido.calculaValorTotal();
			pedido.adicionarPedidoAoItem();
			pedido.setStatus(StatusPedido.CRIADO);
			
			return pedidoRepository.save(pedido);
		} catch (RestauranteNaoEncontradoException | CidadeNaoEncontradaException e) {
			throw new EntidadeRelacionamentoNaoEncontradaException(e.getMessage());
		} 
	}
	
	private Usuario getCliente() {
		return usuarioService.buscar(1L);
	}
}
