package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.controller.UsuarioController;
import br.com.felipe.gorisfood.api.v1.model.response.UsuarioResponseDTO;
import br.com.felipe.gorisfood.core.security.AuthUserSecurity;
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

	@Autowired
	private AuthUserSecurity authUserSecurity;
	
	public UsuarioResponseDTO toModel(Usuario usuario) {
		UsuarioResponseDTO usuarioResponseDTO = createModelWithId(usuario.getId(), usuario);
		mapper.map(usuario, usuarioResponseDTO);
		
		if(authUserSecurity.podeConsultarUsuariosGruposPermissoes()) {
			usuarioResponseDTO
				.add(gorisLinks.linkToUsuarios("usuarios"))
				.add(gorisLinks.linkToGruposUsuario(usuario.getId(), "grupos-usuario"));
		}
		
		return usuarioResponseDTO;
	}
	
	@Override
	public CollectionModel<UsuarioResponseDTO> toCollectionModel(Iterable<? extends Usuario> entities) {
		return super.toCollectionModel(entities)
				.add(gorisLinks.linkToUsuarios());
	}
	
	
}
