package br.com.felipe.gorisfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.GorisLinks;
import br.com.felipe.gorisfood.api.controller.PedidoController;
import br.com.felipe.gorisfood.api.model.response.PedidoResponseDTO;
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
		
		pedidoResponseDTO.add(gorisLinks.linkToPedidos());
		pedidoResponseDTO.getRestaurante().add(gorisLinks.linkToRestaurante(pedidoResponseDTO.getRestaurante().getId()));
		pedidoResponseDTO.getCliente().add(gorisLinks.linkToUsuario(pedidoResponseDTO.getCliente().getId()));
		pedidoResponseDTO.getFormaPagamento().add(gorisLinks.linkToFormaPagamento(pedidoResponseDTO.getFormaPagamento().getId()));
		pedidoResponseDTO.getEnderecoEntrega().add(gorisLinks.linkToCidade(pedido.getEnderecoEntrega().getCidadeId(), "cidade"));
		pedidoResponseDTO.getEnderecoEntrega().add(gorisLinks.linkToEstado(pedido.getEnderecoEntrega().getEstadoId(), "estado"));
		
		pedidoResponseDTO.getItens()
			.forEach( item -> 
				item.add(gorisLinks.linkToProduto(item.getProdutoId(), pedidoResponseDTO.getRestaurante().getId(), "produto"))
			);

		return pedidoResponseDTO;
	}

}
