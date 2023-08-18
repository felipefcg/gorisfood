package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.controller.PedidoController;
import br.com.felipe.gorisfood.api.v1.model.response.PedidoResponseDTO;
import br.com.felipe.gorisfood.core.security.AuthUserSecurity;
import br.com.felipe.gorisfood.domain.model.Pedido;

@Component
public class PedidoResponseDtoAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResponseDTO> {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private GorisLinks gorisLinks;

	@Autowired
	private AuthUserSecurity authUserSecurity;

	public PedidoResponseDtoAssembler() {
		super(PedidoController.class, PedidoResponseDTO.class);
	}
	
	public PedidoResponseDTO toModel(Pedido pedido) {
		var pedidoResponseDTO = createModelWithId(pedido.getId(), pedido);
		mapper.map(pedido, pedidoResponseDTO);
		
		// Não usei o método algaSecurity.podePesquisarPedidos(clienteId, restauranteId) aqui,
	    // porque na geração do link, não temos o id do cliente e do restaurante, 
	    // então precisamos saber apenas se a requisição está autenticada e tem o escopo de leitura
		if(authUserSecurity.podePesquisarPedidos()) {
			pedidoResponseDTO.add(gorisLinks.linkToPedidos("pedidos"));
		}
		
		if(authUserSecurity.podeConsultarRestaurantes()) {
			pedidoResponseDTO.getRestaurante()
				.add(gorisLinks.linkToRestaurante(pedidoResponseDTO.getRestaurante().getId()));
			
			pedidoResponseDTO.getItens()
				.forEach( item -> 
					item.add(gorisLinks.linkToProduto(item.getProdutoId(), pedidoResponseDTO.getRestaurante().getId(), "produto"))
				);
		}
		
		if(authUserSecurity.podeConsultarUsuariosGruposPermissoes()) {
			pedidoResponseDTO.getCliente()
				.add(gorisLinks.linkToUsuario(pedidoResponseDTO.getCliente().getId()));
		}
		
		if(authUserSecurity.podeConsultarFormasPagamento()) {
			pedidoResponseDTO.getFormaPagamento()
				.add(gorisLinks.linkToFormaPagamento(pedidoResponseDTO.getFormaPagamento().getId()));
		}
		
		if(authUserSecurity.podeConsultarCidades()) {
			pedidoResponseDTO.getEnderecoEntrega()
				.add(gorisLinks.linkToCidade(pedido.getEnderecoEntrega().getCidadeId(), "cidade"))
				.add(gorisLinks.linkToEstado(pedido.getEnderecoEntrega().getEstadoId(), "estado"));
		}
		
		

		var codigoPedido = pedido.getCodigo();
		
		if(authUserSecurity.podeGerenciarPedido(pedido.getCodigo())) { 
			if(pedido.podeConfirmar()) {
				pedidoResponseDTO.add(gorisLinks.confirmacaoPedido(codigoPedido, "confirmar"));
			}
			
			if(pedido.podeCancelar()) {
				pedidoResponseDTO.add(gorisLinks.cancelamentoPedido(codigoPedido, "cancelar"));
			}
			
			if(pedido.podeEntregar()) {
				pedidoResponseDTO.add(gorisLinks.entregaPedido(codigoPedido, "entregar"));
			}
		}
		
		return pedidoResponseDTO;
	}

}
