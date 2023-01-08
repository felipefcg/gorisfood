package br.com.felipe.gorisfood.api.assembler;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.request.CozinhaRequestDTO;
import br.com.felipe.gorisfood.domain.model.Cozinha;

@Component
public class CozinhaRequestDesassembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public Cozinha toModel(CozinhaRequestDTO cozinhaDTO) {
		return mapper.map(cozinhaDTO, Cozinha.class);
	}

	public void copyDtoToModel(@Valid CozinhaRequestDTO cozinhaDTO, Cozinha cozinha) {
		mapper.map(cozinhaDTO, cozinha);
	}
}
