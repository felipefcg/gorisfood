package br.com.felipe.gorisfood.api.assembler;

import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.GorisLinks;
import br.com.felipe.gorisfood.api.controller.PermissoesController;
import br.com.felipe.gorisfood.api.model.response.PermissaoResponseDTO;
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
	
	@Override
	public PermissaoResponseDTO toModel(Permissao permissao) {
		return mapper.map(permissao, PermissaoResponseDTO.class);
	}
	
	@Override
	public CollectionModel<PermissaoResponseDTO> toCollectionModel(Iterable<? extends Permissao> permissoes) {
		return super.toCollectionModel(permissoes)
				.add(gorisLinks.linkToPermissoes());
	}
}
