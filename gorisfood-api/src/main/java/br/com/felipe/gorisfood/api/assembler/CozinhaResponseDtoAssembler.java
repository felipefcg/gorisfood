package br.com.felipe.gorisfood.api.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.response.CozinhaResponseDTO;
import br.com.felipe.gorisfood.domain.model.Cozinha;

@Component
public class CozinhaResponseDtoAssembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public CozinhaResponseDTO toDto(Cozinha cozinha) {
		return mapper.map(cozinha, CozinhaResponseDTO.class);
	}
	
	public List<CozinhaResponseDTO> toDtoList(List<Cozinha> cozinhas) {
		var typeToken = new TypeToken<List<CozinhaResponseDTO>>() {}.getType(); 
		return mapper.map(cozinhas, typeToken); 
	}
}
