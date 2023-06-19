package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.model.request.GrupoRequestDTO;
import br.com.felipe.gorisfood.domain.model.Grupo;

@Component
public class GrupoRequestDtoDesassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Grupo toModel(GrupoRequestDTO dto) {
		return mapper.map(dto, Grupo.class);
	}

	public void copyDtotoModel(GrupoRequestDTO dto, Grupo grupo) {
		mapper.map(dto, grupo);
	}
}
