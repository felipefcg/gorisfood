package br.com.felipe.gorisfood.api.v1.assembler;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.controller.RestauranteProdutoContoller;
import br.com.felipe.gorisfood.api.v1.model.response.ProdutoResponseDTO;
import br.com.felipe.gorisfood.core.security.AuthUserSecurity;
import br.com.felipe.gorisfood.domain.model.Produto;

@Component
public class ProdutoResponseDtoAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoResponseDTO> {
	
	public ProdutoResponseDtoAssembler() {
		super(RestauranteProdutoContoller.class, ProdutoResponseDTO.class);
	}

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private GorisLinks gorisLinks;
	
	@Autowired
	private AuthUserSecurity authUserSecurity;
	
	@Override
	public ProdutoResponseDTO toModel(Produto produto) {
		var restauranteId = produto.getRestaurante().getId();
		
		var model = createModelWithId(produto.getId(), produto, produto.getRestaurante().getId());
		
		if(authUserSecurity.podeConsultarRestaurantes()) {
			model.add(gorisLinks.linkToProdutos(restauranteId, "produtos"))
				 .add(gorisLinks.linkToProdutoFoto(produto.getRestaurante().getId(), produto.getId(), "foto"));
		}
		mapper.map(produto, model);
		
		return model;
	}
	
	public List<ProdutoResponseDTO>	toDtoList(Collection<Produto> produtos) {
		var tokenType = new TypeToken<List<ProdutoResponseDTO>>() {};
		return mapper.map(produtos,tokenType.getType());
	}

	public CollectionModel<ProdutoResponseDTO> toCollectionModel(Iterable<? extends Produto> produtos, Long restauranteId) {
			
		return super.toCollectionModel(produtos)
				.add(gorisLinks.linkToProdutos(restauranteId));
	}
}
