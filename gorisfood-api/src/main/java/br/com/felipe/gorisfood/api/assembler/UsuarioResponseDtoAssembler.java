package br.com.felipe.gorisfood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.controller.UsuarioController;
import br.com.felipe.gorisfood.api.controller.UsuarioGrupoController;
import br.com.felipe.gorisfood.api.model.response.UsuarioResponseDTO;
import br.com.felipe.gorisfood.domain.model.Usuario;

@Component
public class UsuarioResponseDtoAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioResponseDTO> {
	private static final Class<UsuarioController> CONTROLLER_CLASS = UsuarioController.class;
	
	public UsuarioResponseDtoAssembler() {
		super(CONTROLLER_CLASS, UsuarioResponseDTO.class);
	}

	@Autowired
	private ModelMapper mapper;
	
	public UsuarioResponseDTO toModel(Usuario usuario) {
		UsuarioResponseDTO usuarioResponseDTO = createModelWithId(usuario.getId(), usuario);
		mapper.map(usuario, usuarioResponseDTO);

		usuarioResponseDTO
			.add(
				linkTo(
					methodOn(CONTROLLER_CLASS)
					.listar())
				.withRel("usuarios")
			);
		
		usuarioResponseDTO.add(
				linkTo(
					methodOn(UsuarioGrupoController.class)
					.listar(usuarioResponseDTO.getId()))
				.withRel("grupos-usuario"));
		
		return usuarioResponseDTO;
	}
	
	@Override
	public CollectionModel<UsuarioResponseDTO> toCollectionModel(Iterable<? extends Usuario> entities) {
		return super.toCollectionModel(entities)
				.add(linkTo(
						methodOn(CONTROLLER_CLASS)
						.listar())
					.withSelfRel());
	}
	
	
}
