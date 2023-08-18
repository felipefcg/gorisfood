package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.controller.RestauranteController;
import br.com.felipe.gorisfood.api.v1.model.response.RestauranteApenasNomeResponseDTO;
import br.com.felipe.gorisfood.core.security.AuthUserSecurity;
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

	@Autowired
	private AuthUserSecurity authUserSecurity;

	@Override
	public RestauranteApenasNomeResponseDTO toModel(Restaurante restaurante) {
		var apenasNomeResponseDtoAssembler = createModelWithId(restaurante.getId(), restaurante);
		mapper.map(restaurante, apenasNomeResponseDtoAssembler);
		
		if(authUserSecurity.podeConsultarRestaurantes()) {
			apenasNomeResponseDtoAssembler.add(gorisLinks.linkToRestaurantes("restaurantes"));
		}
		
		return apenasNomeResponseDtoAssembler;
	}
	
	@Override
	public CollectionModel<RestauranteApenasNomeResponseDTO> toCollectionModel(
			Iterable<? extends Restaurante> restaurantes) {
		
		var collectionModel = super.toCollectionModel(restaurantes);
		
		if(authUserSecurity.podeConsultarRestaurantes()) {
			collectionModel.add(gorisLinks.linkToRestaurantes());
		}
		
		return collectionModel;
	}

}
