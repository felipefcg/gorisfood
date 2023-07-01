package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.controller.PedidoController;
import br.com.felipe.gorisfood.api.v1.model.response.PedidoResponseDTO;
import br.com.felipe.gorisfood.domain.model.Pedido;

@Component
public class PedidoResponseDtoAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResponseDTO> {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private GorisLinks gorisLinks;
	
	public PedidoResponseDtoAssembler() {
		super(PedidoController.class, PedidoResponseDTO.class);
	}
	
	public PedidoResponseDTO toModel(Pedido pedido) {
		var pedidoResponseDTO = createModelWithId(pedido.getId(), pedido);
		mapper.map(pedido, pedidoResponseDTO);
		
		pedidoResponseDTO.add(gorisLinks.linkToPedidos("pedidos"));
		pedidoResponseDTO.getRestaurante().add(gorisLinks.linkToRestaurante(pedidoResponseDTO.getRestaurante().getId()));
		pedidoResponseDTO.getCliente().add(gorisLinks.linkToUsuario(pedidoResponseDTO.getCliente().getId()));
		pedidoResponseDTO.getFormaPagamento().add(gorisLinks.linkToFormaPagamento(pedidoResponseDTO.getFormaPagamento().getId()));
		pedidoResponseDTO.getEnderecoEntrega()
			.add(gorisLinks.linkToCidade(pedido.getEnderecoEntrega().getCidadeId(), "cidade"))
			.add(gorisLinks.linkToEstado(pedido.getEnderecoEntrega().getEstadoId(), "estado"));
		
		pedidoResponseDTO.getItens()
			.forEach( item -> 
				item.add(gorisLinks.linkToProduto(item.getProdutoId(), pedidoResponseDTO.getRestaurante().getId(), "produto"))
			);

		var codigoPedido = pedido.getCodigo();
		if(pedido.podeConfirmar()) {
			pedidoResponseDTO.add(gorisLinks.confirmacaoPedido(codigoPedido, "confirmar"));
		}
		
		if(pedido.podeCancelar()) {
			pedidoResponseDTO.add(gorisLinks.cancelamentoPedido(codigoPedido, "cancelar"));
		}
		
		if(pedido.podeEntregar()) {
			pedidoResponseDTO.add(gorisLinks.entregaPedido(codigoPedido, "entregar"));
		}
		
		return pedidoResponseDTO;
	}

}