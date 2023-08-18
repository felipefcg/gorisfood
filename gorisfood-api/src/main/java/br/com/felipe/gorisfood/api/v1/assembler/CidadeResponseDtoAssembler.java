package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.controller.CidadeController;
import br.com.felipe.gorisfood.api.v1.model.response.CidadeResponseDTO;
import br.com.felipe.gorisfood.core.security.AuthUserSecurity;
import br.com.felipe.gorisfood.domain.model.Cidade;

@Component
public class CidadeResponseDtoAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeResponseDTO> {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private GorisLinks gorisLinks;
	
	@Autowired
	private AuthUserSecurity authUserSecurity;

	public CidadeResponseDtoAssembler() {
		super(CidadeController.class, CidadeResponseDTO.class);
	}
	
	public CidadeResponseDTO toModel(Cidade cidade) {
		
		CidadeResponseDTO cidadeModel = createModelWithId(cidade.getId(), cidade);
		mapper.map(cidade, cidadeModel);
		
		if(authUserSecurity.podeConsultarCidades()) {
			cidadeModel.add(gorisLinks.linkToCidades("cidades"));
		}
		
		if(authUserSecurity.podeConsultarEstados()) {
			cidadeModel.getEstado()
				.add(gorisLinks.linkToEstado(cidadeModel.getEstado().getId()));
		}
		
		return cidadeModel;
	}
	
	
	@Override
	public CollectionModel<CidadeResponseDTO> toCollectionModel(Iterable<? extends Cidade> entities) {
		var collectionModel = super.toCollectionModel(entities);
		
		if(authUserSecurity.podeConsultarCidades()) {
			collectionModel.add(gorisLinks.linkToCidades());
		}
		
		return collectionModel;
	}
	
}
