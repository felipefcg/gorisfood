package br.com.felipe.gorisfood.api.assembler;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.output.RestauranteOutputDTO;
import br.com.felipe.gorisfood.domain.model.Restaurante;

@Component
public class RestauranteOutputDtoAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public RestauranteOutputDTO toDTO(Restaurante restaurante) {
		
		return modelMapper.map(restaurante, RestauranteOutputDTO.class); 
		
	}
	
	public List<RestauranteOutputDTO> toDtoList(List<Restaurante> restaurantes) {
		return restaurantes.stream()
					.map(r -> toDTO(r))
					.toList();
	}
	
	public Optional<RestauranteOutputDTO> toDTO(Optional<Restaurante> restaurante) {
		if(restaurante.isEmpty()) {
			return Optional.empty();
		}
		
		return Optional.of(toDTO(restaurante.get()));
		
	}
}
