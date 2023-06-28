package br.com.felipe.gorisfood.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.controller.CidadeController;
import br.com.felipe.gorisfood.api.v2.GorisLinksV2;
import br.com.felipe.gorisfood.api.v2.model.response.CidadeResponseDTOV2;
import br.com.felipe.gorisfood.domain.model.Cidade;

@Component
public class CidadeResponseDtoAssemblerV2 extends RepresentationModelAssemblerSupport<Cidade, CidadeResponseDTOV2> {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private GorisLinksV2 gorisLinks;
	
	public CidadeResponseDtoAssemblerV2() {
		super(CidadeController.class, CidadeResponseDTOV2.class);
	}
	
	public CidadeResponseDTOV2 toModel(Cidade cidade) {
		
		CidadeResponseDTOV2 cidadeModel = createModelWithId(cidade.getId(), cidade);
		mapper.map(cidade, cidadeModel);
		
		cidadeModel.add(gorisLinks.linkToCidades("cidades"));

		return cidadeModel;
	}
	
	
	@Override
	public CollectionModel<CidadeResponseDTOV2> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities)
				.add(gorisLinks.linkToCidades());
	}
	
}
