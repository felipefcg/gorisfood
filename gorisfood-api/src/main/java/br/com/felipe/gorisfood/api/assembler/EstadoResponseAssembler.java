package br.com.felipe.gorisfood.api.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.response.EstadoResponseDTO;
import br.com.felipe.gorisfood.domain.model.Estado;

@Component
public class EstadoResponseAssembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public EstadoResponseDTO toDto(Estado estado) {
		return mapper.map(estado, EstadoResponseDTO.class);
	}
	
	public List<EstadoResponseDTO> toDtoList(List<Estado> estados) {
		var typeToken = new TypeToken<List<EstadoResponseDTO>>() {};
		return mapper.map(estados, typeToken.getType());
	}
}
