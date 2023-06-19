package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.controller.PedidoController;
import br.com.felipe.gorisfood.api.v1.model.response.PedidoResumidoResponseDTO;
import br.com.felipe.gorisfood.domain.model.Pedido;

@Component
public class PedidoResumidoResponseDtoAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumidoResponseDTO> {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private GorisLinks gorisLinks;
	
	public PedidoResumidoResponseDtoAssembler() {
		super(PedidoController.class, PedidoResumidoResponseDTO.class);
	}
	
	@Override
	public PedidoResumidoResponseDTO toModel(Pedido pedido) {
		var pedidoResumidoResponseDTO = createModelWithId(pedido.getId(), pedido);
		mapper.map(pedido, pedidoResumidoResponseDTO);
		
		pedidoResumidoResponseDTO.add(gorisLinks.linkToPedidos("pedidos"));
		
		pedidoResumidoResponseDTO.getRestaurante().add(
				gorisLinks.linkToRestaurante(pedido.getRestaurante().getId()));
		
		pedidoResumidoResponseDTO.getCliente().add(
				gorisLinks.linkToUsuario(pedido.getCliente().getId()));
		
		return pedidoResumidoResponseDTO;
	}
	
}
