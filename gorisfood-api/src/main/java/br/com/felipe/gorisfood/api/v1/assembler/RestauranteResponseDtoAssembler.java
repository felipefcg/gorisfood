package br.com.felipe.gorisfood.api.v1.assembler;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.controller.RestauranteController;
import br.com.felipe.gorisfood.api.v1.model.response.CozinhaResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.EnderecoResponseDTO;
import br.com.felipe.gorisfood.api.v1.model.response.RestauranteResponseDTO;
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
		
		var restauranteResponseDTO = createModelWithId(restaurante.getId(), restaurante)
				.add(gorisLinks.linkToRestaurantes("restaurantes"))
				.add(gorisLinks.linkToFormasPagamentoRestaurantes(restaurante.getId(), "formasPagamento"))
				.add(gorisLinks.linkToResponsaveisRestaurante(restaurante.getId(), "responsaveis"))
				.add(gorisLinks.linkToProdutos(restaurante.getId(), "produtos"));
		
		modelMapper.map(restaurante, restauranteResponseDTO);
		
		restauranteResponseDTO.getCozinha()
			.add(gorisLinks.linkToCozinha(restaurante.getCozinha().getId()));
		
		if(restauranteResponseDTO.getEndereco() != null) {
			restauranteResponseDTO.getEndereco()
				.add(gorisLinks.linkToCidade(restaurante.getEndereco().getCidadeId(), "cidade"))
				.add(gorisLinks.linkToEstado(restaurante.getEndereco().getEstadoId(), "estado"));
		}
		
		if(restaurante.ativacaoPermitida()) {
			restauranteResponseDTO.add(gorisLinks.linkToRestauranteInativar(restaurante.getId(), "inativar"));
		} 
		
		if(restaurante.inativacaoPermitida()){
			restauranteResponseDTO.add(gorisLinks.linkToRestauranteAtivar(restaurante.getId(), "ativar"));
		}
		
		if(restaurante.aberturaPermitida()){
			restauranteResponseDTO.add(gorisLinks.linkToRestauranteAbrir(restaurante.getId(), "abrir"));
		}
		
		if(restaurante.fechamentoPermitido()) {
			restauranteResponseDTO.add(gorisLinks.linkToRestauranteFechar(restaurante.getId(), "fechar"));
		} 
		

		return restauranteResponseDTO;
		
	}
	
	@Override
	public CollectionModel<RestauranteResponseDTO> toCollectionModel(Iterable<? extends Restaurante> restaurantes) {
		return super.toCollectionModel(restaurantes)
				.add(gorisLinks.linkToRestaurantes());
	}

}