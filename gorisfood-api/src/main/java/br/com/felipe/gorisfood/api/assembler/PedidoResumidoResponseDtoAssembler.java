package br.com.felipe.gorisfood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.controller.PedidoController;
import br.com.felipe.gorisfood.api.controller.RestauranteController;
import br.com.felipe.gorisfood.api.controller.UsuarioController;
import br.com.felipe.gorisfood.api.model.response.PedidoResumidoResponseDTO;
import br.com.felipe.gorisfood.api.model.response.RestauranteResumidoResponseDTO;
import br.com.felipe.gorisfood.api.model.response.UsuarioResponseDTO;
import br.com.felipe.gorisfood.domain.model.Pedido;

@Component
public class PedidoResumidoResponseDtoAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumidoResponseDTO> {

	private static final Class<PedidoController> PEDIDO_CONTROLLER_CLASS = PedidoController.class;
	
	@Autowired
	private ModelMapper mapper;
	
	public PedidoResumidoResponseDtoAssembler() {
		super(PEDIDO_CONTROLLER_CLASS, PedidoResumidoResponseDTO.class);
	}
	
	@Override
	public PedidoResumidoResponseDTO toModel(Pedido pedido) {
		var pedidoResumidoResponseDTO = createModelWithId(pedido.getId(), pedido);
		mapper.map(pedido, pedidoResumidoResponseDTO);
		
		pedidoResumidoResponseDTO.add(
			linkTo(
				methodOn(PEDIDO_CONTROLLER_CLASS)
				.pesquisar(null, null))
			.withRel("pedidos"));
		
		montaLinkRestaurante(pedidoResumidoResponseDTO.getRestaurante());
		montaLinkUsuario(pedidoResumidoResponseDTO.getCliente());
		
		return pedidoResumidoResponseDTO;
	}
	
	private void montaLinkRestaurante(RestauranteResumidoResponseDTO restaurante) {
		restaurante.add(
			linkTo(
				methodOn(RestauranteController.class)
				.buscar(restaurante.getId()))
			.withSelfRel());
	}
	
	private void montaLinkUsuario(UsuarioResponseDTO cliente) {
		cliente.add(
			linkTo(
				methodOn(UsuarioController.class)
				.buscar(cliente.getId()))
			.withSelfRel());
	}

}
