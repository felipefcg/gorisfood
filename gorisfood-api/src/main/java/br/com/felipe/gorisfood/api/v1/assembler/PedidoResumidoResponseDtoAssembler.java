package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.controller.PedidoController;
import br.com.felipe.gorisfood.api.v1.model.response.PedidoResumidoResponseDTO;
import br.com.felipe.gorisfood.core.security.AuthUserSecurity;
import br.com.felipe.gorisfood.domain.model.Pedido;

@Component
public class PedidoResumidoResponseDtoAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumidoResponseDTO> {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private GorisLinks gorisLinks;

	@Autowired
	private AuthUserSecurity authUserSecurity;

	public PedidoResumidoResponseDtoAssembler() {
		super(PedidoController.class, PedidoResumidoResponseDTO.class);
	}
	
	@Override
	public PedidoResumidoResponseDTO toModel(Pedido pedido) {
		var pedidoResumidoResponseDTO = createModelWithId(pedido.getId(), pedido);
		mapper.map(pedido, pedidoResumidoResponseDTO);
		
		if(authUserSecurity.podePesquisarPedidos()) {
			pedidoResumidoResponseDTO.add(gorisLinks.linkToPedidos("pedidos"));
		}
		
		if(authUserSecurity.podeConsultarRestaurantes()) {
			pedidoResumidoResponseDTO.getRestaurante().add(
				gorisLinks.linkToRestaurante(pedido.getRestaurante().getId()));
		}
		
		if(authUserSecurity.podeConsultarUsuariosGruposPermissoes()) {
			pedidoResumidoResponseDTO.getCliente().add(
				gorisLinks.linkToUsuario(pedido.getCliente().getId()));
		}
		
		return pedidoResumidoResponseDTO;
	}
	
}
