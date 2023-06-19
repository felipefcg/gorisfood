package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.controller.RestauranteController;
import br.com.felipe.gorisfood.api.v1.model.response.RestauranteApenasNomeResponseDTO;
import br.com.felipe.gorisfood.domain.model.Restaurante;

@Component
public class RestauranteApenasNomeResponseDtoAssembler
		extends RepresentationModelAssemblerSupport<Restaurante, RestauranteApenasNomeResponseDTO> {

	public RestauranteApenasNomeResponseDtoAssembler() {
		super(RestauranteController.class, RestauranteApenasNomeResponseDTO.class);
	}

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private GorisLinks gorisLinks;
	
	@Override
	public RestauranteApenasNomeResponseDTO toModel(Restaurante restaurante) {
		var apenasNomeResponseDtoAssembler = createModelWithId(restaurante.getId(), restaurante);
		mapper.map(restaurante, apenasNomeResponseDtoAssembler);
		
		apenasNomeResponseDtoAssembler.add(gorisLinks.linkToRestaurantes("restaurantes"));
		return apenasNomeResponseDtoAssembler;
	}
	
	@Override
	public CollectionModel<RestauranteApenasNomeResponseDTO> toCollectionModel(
			Iterable<? extends Restaurante> restaurantes) {
		return super.toCollectionModel(restaurantes)
				.add(gorisLinks.linkToRestaurantes());
	}

}
