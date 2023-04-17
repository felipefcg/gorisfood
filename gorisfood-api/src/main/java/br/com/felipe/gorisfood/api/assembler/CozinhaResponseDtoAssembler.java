package br.com.felipe.gorisfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.GorisLinks;
import br.com.felipe.gorisfood.api.controller.CozinhaController;
import br.com.felipe.gorisfood.api.model.response.CozinhaResponseDTO;
import br.com.felipe.gorisfood.domain.model.Cozinha;

@Component
public class CozinhaResponseDtoAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaResponseDTO> {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private GorisLinks gorisLinks;
	
	public CozinhaResponseDtoAssembler() {
		super(CozinhaController.class, CozinhaResponseDTO.class);
	}
	
	@Override
	public CozinhaResponseDTO toModel(Cozinha cozinha) {
		var cozinhaResponseDTO = createModelWithId(cozinha.getId(), cozinha);
		mapper.map(cozinha, cozinhaResponseDTO);
		
		cozinhaResponseDTO.add(gorisLinks.linkToCozinhas("cozinhas"));
		return cozinhaResponseDTO;
	}
	
}
