package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.controller.RestauranteController;
import br.com.felipe.gorisfood.api.v1.model.response.RestauranteResponseDTO;
import br.com.felipe.gorisfood.core.security.AuthUserSecurity;
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
	
	@Autowired
	private AuthUserSecurity authUserSecurity;
	
	@Override
	public RestauranteResponseDTO toModel(Restaurante restaurante) {
		
		var restauranteResponseDTO = createModelWithId(restaurante.getId(), restaurante);
		modelMapper.map(restaurante, restauranteResponseDTO);
		
		if(authUserSecurity.podeConsultarRestaurantes()) {
			restauranteResponseDTO
				.add(gorisLinks.linkToRestaurantes("restaurantes"))
				.add(gorisLinks.linkToFormasPagamentoRestaurantes(restaurante.getId(), "formas-pagamento"))
				.add(gorisLinks.linkToProdutos(restaurante.getId(), "produtos"))
			;
		}
		
		if(authUserSecurity.podeGerenciarCadastroRestaurantes()) {
			restauranteResponseDTO.add(gorisLinks.linkToResponsaveisRestaurante(restaurante.getId(), "responsaveis"));
			
			if(restaurante.ativacaoPermitida()) {
				restauranteResponseDTO.add(gorisLinks.linkToRestauranteInativar(restaurante.getId(), "inativar"));
			} 
			
			if(restaurante.inativacaoPermitida()){
				restauranteResponseDTO.add(gorisLinks.linkToRestauranteAtivar(restaurante.getId(), "ativar"));
			}
		}		
		
		if(authUserSecurity.podeConsultarCozinhas()) {
			restauranteResponseDTO.getCozinha()
				.add(gorisLinks.linkToCozinha(restaurante.getCozinha().getId()));
		}
		
		if(authUserSecurity.podeConsultarCidades() 
				&& restauranteResponseDTO.getEndereco() != null) {
			restauranteResponseDTO.getEndereco()
				.add(gorisLinks.linkToCidade(restaurante.getEndereco().getCidadeId(), "cidade"))
				.add(gorisLinks.linkToEstado(restaurante.getEndereco().getEstadoId(), "estado"));
		}
		
		if(authUserSecurity.podeGerenciarFuncionamentoRestaurantes(restaurante.getId())) {
		
			if(restaurante.aberturaPermitida()){
				restauranteResponseDTO.add(gorisLinks.linkToRestauranteAbrir(restaurante.getId(), "abrir"));
			}
			
			if(restaurante.fechamentoPermitido()) {
				restauranteResponseDTO.add(gorisLinks.linkToRestauranteFechar(restaurante.getId(), "fechar"));
			} 
		
		}
		
		return restauranteResponseDTO;
		
	}
	
	@Override
	public CollectionModel<RestauranteResponseDTO> toCollectionModel(Iterable<? extends Restaurante> restaurantes) {
		var collectionModel = super.toCollectionModel(restaurantes);
		
		if(authUserSecurity.podeConsultarRestaurantes()) {
			collectionModel.add(gorisLinks.linkToRestaurantes());
		}
		
		return collectionModel;
				
	}

}
