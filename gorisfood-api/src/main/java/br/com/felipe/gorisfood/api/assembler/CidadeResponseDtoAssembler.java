package br.com.felipe.gorisfood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.controller.CidadeController;
import br.com.felipe.gorisfood.api.controller.EstadoController;
import br.com.felipe.gorisfood.api.model.response.CidadeResponseDTO;
import br.com.felipe.gorisfood.domain.model.Cidade;

@Component
public class CidadeResponseDtoAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeResponseDTO> {

	@Autowired
	private ModelMapper mapper;

	public CidadeResponseDtoAssembler() {
		super(CidadeController.class, CidadeResponseDTO.class);
	}
	
	public CidadeResponseDTO toModel(Cidade cidade) {
		
		CidadeResponseDTO cidadeModel = createModelWithId(cidade.getId(), cidade);
		mapper.map(cidade, cidadeModel);
		
		cidadeModel.add(linkTo(
						 methodOn(CidadeController.class)
					 	.listar())
					.withRel("cidades"));

		cidadeModel.getEstado()
					.add(linkTo(
					   methodOn(EstadoController.class)
					   .buscar(cidadeModel.getEstado().getId()))
					.withSelfRel());
		
		return cidadeModel;
	}
	
	
	@Override
	public CollectionModel<CidadeResponseDTO> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities)
				.add(linkTo(
						methodOn(CidadeController.class)
						.listar())
					.withSelfRel());
	}
	
}
