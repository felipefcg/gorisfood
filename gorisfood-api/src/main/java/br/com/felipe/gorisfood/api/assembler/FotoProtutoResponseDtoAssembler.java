package br.com.felipe.gorisfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.GorisLinks;
import br.com.felipe.gorisfood.api.controller.RestauranteProdutoFotoController;
import br.com.felipe.gorisfood.api.model.response.FotoProdutoReponseDTO;
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
	
	@Override
	public FotoProdutoReponseDTO toModel(FotoProduto model) {
		var dto = createModelWithId(model.getId(), model, model.getProdutoId(), model.getRestauranteId())
					.add(gorisLinks.linkToProduto(model.getProdutoId(), model.getRestauranteId(), "produto"));
		
		mapper.map(model, dto);
		return dto;
	}
}
