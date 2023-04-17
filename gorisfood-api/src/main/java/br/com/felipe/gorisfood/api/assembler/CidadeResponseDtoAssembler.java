package br.com.felipe.gorisfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.GorisLinks;
import br.com.felipe.gorisfood.api.controller.CidadeController;
import br.com.felipe.gorisfood.api.model.response.CidadeResponseDTO;
import br.com.felipe.gorisfood.domain.model.Cidade;

@Component
public class CidadeResponseDtoAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeResponseDTO> {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private GorisLinks gorisLinks;
	public CidadeResponseDtoAssembler() {
		super(CidadeController.class, CidadeResponseDTO.class);
	}
	
	public CidadeResponseDTO toModel(Cidade cidade) {
		
		CidadeResponseDTO cidadeModel = createModelWithId(cidade.getId(), cidade);
		mapper.map(cidade, cidadeModel);
		
		cidadeModel.add(gorisLinks.linkToCidades("cidades"));

		cidadeModel.getEstado()
					.add(gorisLinks.linkToEstado(cidadeModel.getEstado().getId()));
		
		return cidadeModel;
	}
	
	
	@Override
	public CollectionModel<CidadeResponseDTO> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities)
				.add(gorisLinks.linkToCidades());
	}
	
}
