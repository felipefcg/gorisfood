package br.com.felipe.gorisfood.api.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.response.CidadeResponseDTO;
import br.com.felipe.gorisfood.domain.model.Cidade;

@Component
public class CidadeResponseDtoAssembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public CidadeResponseDTO toDto(Cidade cidade) {
		return mapper.map(cidade, CidadeResponseDTO.class);
	}
	
	public List<CidadeResponseDTO> toDtoList(List<Cidade> cidades) {
		var listType = new TypeToken<List<CidadeResponseDTO>>() {}.getType();
		return mapper.map(cidades, listType);
	}
}
