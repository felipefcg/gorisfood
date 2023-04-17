package br.com.felipe.gorisfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.GorisLinks;
import br.com.felipe.gorisfood.api.controller.UsuarioController;
import br.com.felipe.gorisfood.api.model.response.UsuarioResponseDTO;
import br.com.felipe.gorisfood.domain.model.Usuario;

@Component
public class UsuarioResponseDtoAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioResponseDTO> {

	public UsuarioResponseDtoAssembler() {
		super(UsuarioController.class, UsuarioResponseDTO.class);
	}

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private GorisLinks gorisLinks;
	
	public UsuarioResponseDTO toModel(Usuario usuario) {
		UsuarioResponseDTO usuarioResponseDTO = createModelWithId(usuario.getId(), usuario);
		mapper.map(usuario, usuarioResponseDTO);

		usuarioResponseDTO.add(gorisLinks.linkToUsuarios("usuarios"));
		
		usuarioResponseDTO.add(gorisLinks.linkToGruposUsuario(usuario.getId(), "grupos-usuario"));
		
		return usuarioResponseDTO;
	}
	
	@Override
	public CollectionModel<UsuarioResponseDTO> toCollectionModel(Iterable<? extends Usuario> entities) {
		return super.toCollectionModel(entities)
				.add(gorisLinks.linkToUsuarios());
	}
	
	
}
