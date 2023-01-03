package br.com.felipe.gorisfood.api.assembler;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.output.CozinhaOutputDTO;
import br.com.felipe.gorisfood.api.model.output.RestauranteOutputDTO;
import br.com.felipe.gorisfood.domain.model.Restaurante;

@Component
public class RestauranteOutputDtoAssembler {
	
	public RestauranteOutputDTO toDTO(Restaurante restaurante) {
		CozinhaOutputDTO cozinhaOutputDTO = CozinhaOutputDTO.builder()
												.id(restaurante.getCozinha().getId())
												.nome(restaurante.getCozinha().getNome())
												.build();
		
		return RestauranteOutputDTO.builder()
				.id(restaurante.getId())
				.nome(restaurante.getNome())
				.taxaFrete(restaurante.getTaxaFrete())
				.cozinha(cozinhaOutputDTO)
				.build();
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
