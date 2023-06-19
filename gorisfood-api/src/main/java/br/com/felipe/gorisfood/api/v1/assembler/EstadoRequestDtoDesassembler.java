package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.model.request.EstadoRequestDTO;
import br.com.felipe.gorisfood.domain.model.Estado;

@Component
public class EstadoRequestDtoDesassembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public Estado toModel (EstadoRequestDTO estadoDTO) {
		return mapper.map(estadoDTO, Estado.class);
	}
}
