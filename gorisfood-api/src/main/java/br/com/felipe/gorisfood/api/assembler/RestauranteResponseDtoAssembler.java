package br.com.felipe.gorisfood.api.assembler;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.response.RestauranteResponseDTO;
import br.com.felipe.gorisfood.domain.model.Restaurante;

@Component
public class RestauranteResponseDtoAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public RestauranteResponseDTO toDTO(Restaurante restaurante) {
		
		return modelMapper.map(restaurante, RestauranteResponseDTO.class); 
		
	}
	
	public List<RestauranteResponseDTO> toDtoList(List<Restaurante> restaurantes) {
		return restaurantes.stream()
					.map(r -> toDTO(r))
					.toList();
	}
	
	public Optional<RestauranteResponseDTO> toDTO(Optional<Restaurante> restaurante) {
		if(restaurante.isEmpty()) {
			return Optional.empty();
		}
		
		return Optional.of(toDTO(restaurante.get()));
		
	}
}
