package br.com.felipe.gorisfood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.GorisLinks;
import br.com.felipe.gorisfood.api.controller.CidadeController;
import br.com.felipe.gorisfood.api.controller.EstadoController;
import br.com.felipe.gorisfood.api.controller.FormaPagamentoController;
import br.com.felipe.gorisfood.api.controller.PedidoController;
import br.com.felipe.gorisfood.api.controller.RestauranteController;
import br.com.felipe.gorisfood.api.controller.RestauranteProdutoContoller;
import br.com.felipe.gorisfood.api.controller.UsuarioController;
import br.com.felipe.gorisfood.api.model.response.EnderecoResponseDTO;
import br.com.felipe.gorisfood.api.model.response.FormaPagamentoResponseDTO;
import br.com.felipe.gorisfood.api.model.response.ItemPedidoResponseDTO;
import br.com.felipe.gorisfood.api.model.response.PedidoResponseDTO;
import br.com.felipe.gorisfood.api.model.response.RestauranteResumidoResponseDTO;
import br.com.felipe.gorisfood.api.model.response.UsuarioResponseDTO;
import br.com.felipe.gorisfood.domain.model.Pedido;

@Component
public class PedidoResponseDtoAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResponseDTO> {

	private static final Class<PedidoController> PEDIDO_CONTROLLER_CLASS = PedidoController.class;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private GorisLinks gorisLinks;
	
	public PedidoResponseDtoAssembler() {
		super(PEDIDO_CONTROLLER_CLASS, PedidoResponseDTO.class);
	}
	
	public PedidoResponseDTO toModel(Pedido pedido) {
		var pedidoResponseDTO = createModelWithId(pedido.getId(), pedido);
		mapper.map(pedido, pedidoResponseDTO);
		
		pedidoResponseDTO.add(gorisLinks.linkToPedidos());
		
		montaLinkRestaurante(pedidoResponseDTO.getRestaurante());
		montaLinkUsuario(pedidoResponseDTO.getCliente());
		montaLinkFormaPagamento(pedidoResponseDTO.getFormaPagamento());
		montaLinksEndereco(pedidoResponseDTO.getEnderecoEntrega(), pedido);
		pedidoResponseDTO.getItens()
			.forEach( item -> montaLInkProduto(item, pedidoResponseDTO.getRestaurante().getId()));

		return pedidoResponseDTO;
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

	private void montaLinkFormaPagamento(FormaPagamentoResponseDTO formaPagamento) {
		formaPagamento.add(
			linkTo(
				methodOn(FormaPagamentoController.class)
				.buscar(formaPagamento.getId(), null))
			.withSelfRel());
	}
	
	private void montaLinksEndereco(EnderecoResponseDTO enderecoEntrega, Pedido pedido) {
		Long cidadeId = pedido.getEnderecoEntrega().getCidadeId();
		Long estadoId = pedido.getEnderecoEntrega().getEstadoId();
		
		enderecoEntrega.add(
			linkTo(
				methodOn(CidadeController.class)
				.buscar(cidadeId))
			.withRel("cidade"),
			
			linkTo(
					methodOn(EstadoController.class)
					.buscar(estadoId))
				.withRel("estado"));
		
	}

	private void montaLInkProduto(ItemPedidoResponseDTO item, Long restauranteId) {
		item.add(
			linkTo(
				methodOn(RestauranteProdutoContoller.class)
				.buscar(restauranteId, item.getProdutoId()))
			.withRel("produto"));
	}

	@Override
	public CollectionModel<PedidoResponseDTO> toCollectionModel(Iterable<? extends Pedido> entities) {
		return super.toCollectionModel(entities).add(
				linkTo(PEDIDO_CONTROLLER_CLASS)
				.withSelfRel());
	}
}
