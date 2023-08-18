package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.controller.RestauranteProdutoFotoController;
import br.com.felipe.gorisfood.api.v1.model.response.FotoProdutoReponseDTO;
import br.com.felipe.gorisfood.core.security.AuthUserSecurity;
import br.com.felipe.gorisfood.domain.model.FotoProduto;

@Component
public class FotoProtutoResponseDtoAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoReponseDTO> {

	public FotoProtutoResponseDtoAssembler() {
		super(RestauranteProdutoFotoController.class, FotoProdutoReponseDTO.class);
	}

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private GorisLinks gorisLinks;

	@Autowired
	private AuthUserSecurity authUserSecurity;

	@Override
	public FotoProdutoReponseDTO toModel(FotoProduto model) {
		var dto = mapper.map(model, FotoProdutoReponseDTO.class);
		
		if(authUserSecurity.podeConsultarRestaurantes()) {
			
			dto.add(gorisLinks.linkToProduto(model.getProdutoId(), model.getRestauranteId(), "produto"))
			   .add(gorisLinks.linkToProdutoFoto(model.getId(), model.getProdutoId(), "self"));
		}
		
		
		return dto;
	}
}
