package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.controller.RestauranteController;
import br.com.felipe.gorisfood.api.v1.model.response.RestauranteBasicoResponseDTO;
import br.com.felipe.gorisfood.core.security.AuthUserSecurity;
import br.com.felipe.gorisfood.domain.model.Restaurante;

@Component
public class RestauranteBasicoResponseDTOAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoResponseDTO> {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private GorisLinks gorisLinks;

	@Autowired
	private AuthUserSecurity authUserSecurity;

	public RestauranteBasicoResponseDTOAssembler() {
		super(RestauranteController.class, RestauranteBasicoResponseDTO.class);
	}

	@Override
	public RestauranteBasicoResponseDTO toModel(Restaurante restaurante) {
		var restauranteResumoResponseDTO = createModelWithId(restaurante.getId(), restaurante);
		mapper.map(restaurante, restauranteResumoResponseDTO);
		
		if(authUserSecurity.podeConsultarCozinhas()) {
			var cozinha = restauranteResumoResponseDTO.getCozinha();
			cozinha.add(gorisLinks.linkToCozinha(cozinha.getId()));
		}
		
		if(authUserSecurity.podeConsultarRestaurantes()) {
			restauranteResumoResponseDTO.add(gorisLinks.linkToRestaurantes("restaurantes"));
		}
		
		return restauranteResumoResponseDTO;
	}

	@Override
	public CollectionModel<RestauranteBasicoResponseDTO> toCollectionModel(Iterable<? extends Restaurante> restaurantes) {
		var collectionModel = super.toCollectionModel(restaurantes);
		
		if(authUserSecurity.podeConsultarRestaurantes()) {
			collectionModel.add(gorisLinks.linkToRestaurantes());
		}
		
		return collectionModel;
		
	}
}
