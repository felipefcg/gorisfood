package br.com.felipe.gorisfood.api.v2.assembler;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v2.model.request.CozinhaRequestDTOV2;
import br.com.felipe.gorisfood.domain.model.Cozinha;

@Component
public class CozinhaRequestDesassemblerV2 {
	
	@Autowired
	private ModelMapper mapper;
	
	public Cozinha toModel(CozinhaRequestDTOV2 cozinhaDTO) {
		return mapper.map(cozinhaDTO, Cozinha.class);
	}

	public void copyDtoToModel(@Valid CozinhaRequestDTOV2 cozinhaDTO, Cozinha cozinha) {
		mapper.map(cozinhaDTO, cozinha);
	}
}
