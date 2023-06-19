package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.controller.EstadoController;
import br.com.felipe.gorisfood.api.v1.model.response.EstadoResponseDTO;
import br.com.felipe.gorisfood.domain.model.Estado;

@Component
public class EstadoResponseAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoResponseDTO> {

	public EstadoResponseAssembler() {
		super(EstadoController.class, EstadoResponseDTO.class);
	}

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private GorisLinks gorisLinks;
	
	public EstadoResponseDTO toModel(Estado estado) {
		var estadoResponseDTO = createModelWithId(estado.getId(), estado);
		mapper.map(estado, estadoResponseDTO);
		
		estadoResponseDTO.add(gorisLinks.linkToEstados("estados"));
		return estadoResponseDTO;
	}
	
	@Override
	public CollectionModel<EstadoResponseDTO> toCollectionModel(Iterable<? extends Estado> entities) {
		return super.toCollectionModel(entities)
				.add(gorisLinks.linkToEstados());
	}
	
}
