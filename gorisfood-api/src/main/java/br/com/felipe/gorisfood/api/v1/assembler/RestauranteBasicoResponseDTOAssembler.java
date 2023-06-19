package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.controller.RestauranteController;
import br.com.felipe.gorisfood.api.v1.model.response.RestauranteBasicoResponseDTO;
import br.com.felipe.gorisfood.domain.model.Restaurante;

@Component
public class RestauranteBasicoResponseDTOAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoResponseDTO> {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private GorisLinks gorisLinks;
	
	public RestauranteBasicoResponseDTOAssembler() {
		super(RestauranteController.class, RestauranteBasicoResponseDTO.class);
	}

	@Override
	public RestauranteBasicoResponseDTO toModel(Restaurante restaurante) {
		var restauranteResumoResponseDTO = createModelWithId(restaurante.getId(), restaurante);
		mapper.map(restaurante, restauranteResumoResponseDTO);
		
		var cozinha = restauranteResumoResponseDTO.getCozinha();
		cozinha.add(gorisLinks.linkToCozinha(cozinha.getId()));
		
		restauranteResumoResponseDTO.add(gorisLinks.linkToRestaurantes("restaurantes"));
		
		return restauranteResumoResponseDTO;
	}

	@Override
	public CollectionModel<RestauranteBasicoResponseDTO> toCollectionModel(Iterable<? extends Restaurante> restaurantes) {
		return super.toCollectionModel(restaurantes)
				.add(gorisLinks.linkToRestaurantes());
	}
}
