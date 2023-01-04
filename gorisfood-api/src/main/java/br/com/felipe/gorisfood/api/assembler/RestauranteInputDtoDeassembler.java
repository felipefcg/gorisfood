package br.com.felipe.gorisfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.input.RestauranteInputDTO;
import br.com.felipe.gorisfood.domain.model.Restaurante;

@Component
public class RestauranteInputDtoDeassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurante toDomain(RestauranteInputDTO restauranteDTO) {
		return modelMapper.map(restauranteDTO, Restaurante.class);
	}
}
