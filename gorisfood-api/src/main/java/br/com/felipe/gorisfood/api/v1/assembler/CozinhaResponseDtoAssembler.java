package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.controller.CozinhaController;
import br.com.felipe.gorisfood.api.v1.model.response.CozinhaResponseDTO;
import br.com.felipe.gorisfood.core.security.AuthUserSecurity;
import br.com.felipe.gorisfood.domain.model.Cozinha;

@Component
public class CozinhaResponseDtoAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaResponseDTO> {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private GorisLinks gorisLinks;

	@Autowired
	private AuthUserSecurity authUserSecurity;
	
	public CozinhaResponseDtoAssembler() {
		super(CozinhaController.class, CozinhaResponseDTO.class);
	}
	
	@Override
	public CozinhaResponseDTO toModel(Cozinha cozinha) {
		var cozinhaResponseDTO = createModelWithId(cozinha.getId(), cozinha);
		mapper.map(cozinha, cozinhaResponseDTO);
		if (authUserSecurity.podeConsultarCozinhas()) {
			cozinhaResponseDTO.add(gorisLinks.linkToCozinhas("cozinhas"));
		}
		return cozinhaResponseDTO;
	}
	
}
