package br.com.felipe.gorisfood.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v2.GorisLinksV2;
import br.com.felipe.gorisfood.api.v2.controller.CozinhaControllerV2;
import br.com.felipe.gorisfood.api.v2.model.response.CozinhaResponseDTOV2;
import br.com.felipe.gorisfood.domain.model.Cozinha;

@Component
public class CozinhaResponseDtoAssemblerV2 extends RepresentationModelAssemblerSupport<Cozinha, CozinhaResponseDTOV2> {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private GorisLinksV2 gorisLinks;
	
	public CozinhaResponseDtoAssemblerV2() {
		super(CozinhaControllerV2.class, CozinhaResponseDTOV2.class);
	}
	
	@Override
	public CozinhaResponseDTOV2 toModel(Cozinha cozinha) {
		var cozinhaResponseDTO = createModelWithId(cozinha.getId(), cozinha);
		mapper.map(cozinha, cozinhaResponseDTO);
		
		cozinhaResponseDTO.add(gorisLinks.linkToCozinhas("cozinhas"));
		return cozinhaResponseDTO;
	}
	
}
