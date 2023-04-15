package br.com.felipe.gorisfood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.controller.CozinhaController;
import br.com.felipe.gorisfood.api.model.response.CozinhaResponseDTO;
import br.com.felipe.gorisfood.domain.model.Cozinha;

@Component
public class CozinhaResponseDtoAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaResponseDTO> {

	private static final Class<CozinhaController> COZINHA_CONTROLLER = CozinhaController.class;
	
	@Autowired
	private ModelMapper mapper;
	
	public CozinhaResponseDtoAssembler() {
		super(COZINHA_CONTROLLER, CozinhaResponseDTO.class);
	}
	
	@Override
	public CozinhaResponseDTO toModel(Cozinha cozinha) {
		var cozinhaResponseDTO = createModelWithId(cozinha.getId(), cozinha);
		mapper.map(cozinha, cozinhaResponseDTO);
		
		cozinhaResponseDTO.add(
					linkTo(methodOn(COZINHA_CONTROLLER)
							.listar(null))
					.withRel("cozinhas")
				);
		return cozinhaResponseDTO;
	}
	
}
