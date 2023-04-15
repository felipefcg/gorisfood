package br.com.felipe.gorisfood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.controller.EstadoController;
import br.com.felipe.gorisfood.api.model.response.EstadoResponseDTO;
import br.com.felipe.gorisfood.domain.model.Estado;

@Component
public class EstadoResponseAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoResponseDTO> {

	private static final Class<EstadoController> ESTADO_CONTROLLER = EstadoController.class;
	
	public EstadoResponseAssembler() {
		super(ESTADO_CONTROLLER, EstadoResponseDTO.class);
	}

	@Autowired
	private ModelMapper mapper;
	
	public EstadoResponseDTO toModel(Estado estado) {
		var estadoResponseDTO = createModelWithId(estado.getId(), estado);
		mapper.map(estado, estadoResponseDTO);
		
		estadoResponseDTO.add(
				linkTo(
					methodOn(ESTADO_CONTROLLER)
					.listar())
				.withRel("estados"));
		return estadoResponseDTO;
	}
	
	@Override
	public CollectionModel<EstadoResponseDTO> toCollectionModel(Iterable<? extends Estado> entities) {
		return super.toCollectionModel(entities)
				.add(
					linkTo(
						methodOn(ESTADO_CONTROLLER)
						.listar())
					.withSelfRel());
	}
	
}
