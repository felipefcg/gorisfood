package br.com.felipe.gorisfood.api.assembler;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.GorisLinks;
import br.com.felipe.gorisfood.api.controller.RestauranteController;
import br.com.felipe.gorisfood.api.model.response.CozinhaResponseDTO;
import br.com.felipe.gorisfood.api.model.response.EnderecoResponseDTO;
import br.com.felipe.gorisfood.api.model.response.RestauranteResponseDTO;
import br.com.felipe.gorisfood.domain.model.Restaurante;

@Component
public class RestauranteResponseDtoAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteResponseDTO> {
	
	public RestauranteResponseDtoAssembler() {
		super(RestauranteController.class, RestauranteResponseDTO.class);
	}

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private GorisLinks gorisLinks;
	
	@Override
	public RestauranteResponseDTO toModel(Restaurante restaurante) {
		
		var restauranteResponseDTO = createModelWithId(restaurante.getId(), restaurante);
		modelMapper.map(restaurante, restauranteResponseDTO);
		
		restauranteResponseDTO.getCozinha()
			.add(gorisLinks.linkToCozinha(restaurante.getCozinha().getId()));
		
		restauranteResponseDTO.getEndereco()
			.add(gorisLinks.linkToCidade(restaurante.getEndereco().getCidadeId(), "cidade"))
			.add(gorisLinks.linkToEstado(restaurante.getEndereco().getEstadoId(), "estado"));
		
		restauranteResponseDTO
			.add(gorisLinks.linkToRestaurantes("restaurantes"))
			.add(gorisLinks.linkToFormasPagamentoRestaurantes(restaurante.getId(), "formas-pagamento"))
			.add(gorisLinks.linkToResponsaveisRestaurante(restaurante.getId(), "responsaveis"));
		
		return restauranteResponseDTO;
		
	}
	
	@Override
	public CollectionModel<RestauranteResponseDTO> toCollectionModel(Iterable<? extends Restaurante> restaurantes) {
		return super.toCollectionModel(restaurantes)
				.add(gorisLinks.linkToRestaurantes());
	}

}
