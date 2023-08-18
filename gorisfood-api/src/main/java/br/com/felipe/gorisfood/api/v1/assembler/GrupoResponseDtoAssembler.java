package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.controller.GrupoController;
import br.com.felipe.gorisfood.api.v1.model.response.GrupoResponseDTO;
import br.com.felipe.gorisfood.core.security.AuthUserSecurity;
import br.com.felipe.gorisfood.domain.model.Grupo;

@Component
public class GrupoResponseDtoAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoResponseDTO> {

	public GrupoResponseDtoAssembler() {
		super(GrupoController.class, GrupoResponseDTO.class);
	}

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private GorisLinks gorisLinks;

	@Autowired
	private AuthUserSecurity authUserSecurity;

	@Override
	public GrupoResponseDTO toModel(Grupo grupo) {
		var model = createModelWithId(grupo.getId(), grupo);
		mapper.map(grupo, model);
		
		if(authUserSecurity.podeConsultarUsuariosGruposPermissoes()) {
			model.add(gorisLinks.linkToGrupos("grupos"))
				 .add(gorisLinks.linkToGrupoPermissoes(grupo.getId(), "permissoes"));
		}
		
		return model;
	}
	
	@Override
	public CollectionModel<GrupoResponseDTO> toCollectionModel(Iterable<? extends Grupo> grupos) {
		 var collectionModel = super.toCollectionModel(grupos);
		 
		 if(authUserSecurity.podeConsultarUsuariosGruposPermissoes()) {
				collectionModel.add(gorisLinks.linkToGrupos());
		 }
		 
		 return collectionModel;
	}
	
}
