package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.controller.PermissoesController;
import br.com.felipe.gorisfood.api.v1.model.response.PermissaoResponseDTO;
import br.com.felipe.gorisfood.core.security.AuthUserSecurity;
import br.com.felipe.gorisfood.domain.model.Permissao;

@Component
public class PermissaoResponseDtoAssembler extends RepresentationModelAssemblerSupport<Permissao, PermissaoResponseDTO> {

	public PermissaoResponseDtoAssembler() {
		super(PermissoesController.class, PermissaoResponseDTO.class);
	}

	@Autowired
	private GorisLinks gorisLinks;
	
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private AuthUserSecurity authUserSecurity;

	@Override
	public PermissaoResponseDTO toModel(Permissao permissao) {
		return mapper.map(permissao, PermissaoResponseDTO.class);
	}
	
	@Override
	public CollectionModel<PermissaoResponseDTO> toCollectionModel(Iterable<? extends Permissao> permissoes) {
		 var collectionModel = super.toCollectionModel(permissoes);
		 
		 if(authUserSecurity.podeConsultarUsuariosGruposPermissoes()) {
			collectionModel.add(gorisLinks.linkToPermissoes());
		 }
		 
		 return collectionModel;
	}
}
