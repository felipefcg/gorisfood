package br.com.felipe.gorisfood.api.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.response.GrupoResponseDTO;
import br.com.felipe.gorisfood.domain.model.Grupo;

@Component
public class GrupoResponseDtoAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public GrupoResponseDTO toDto(Grupo model) {
		return mapper.map(model, GrupoResponseDTO.class);
	}
	
	public List<GrupoResponseDTO> toDtoList(List<Grupo> modelList) {
		var typeToken = new TypeToken<List<GrupoResponseDTO>> () {};
		return mapper.map(modelList, typeToken.getType());
	}
}
