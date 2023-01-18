package br.com.felipe.gorisfood.core.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.felipe.gorisfood.api.model.response.EnderecoResponseDTO;
import br.com.felipe.gorisfood.domain.model.Endereco;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper getModelMapper() {
		var modelMapper = new ModelMapper();
		
//		modelMapper.createTypeMap(Restaurante.class, RestauranteResponseDTO.class)
//			.addMapping(Restaurante::getTaxaFrete, RestauranteResponseDTO::setPrecoFrete);
		
		modelMapper.createTypeMap(Endereco.class, EnderecoResponseDTO.class)
			.<String>addMapping(model -> model.getCidade().getNome(), (dto, valor) -> dto.setCidade(valor))
			.<String>addMapping(model -> model.getCidade().getEstado().getNome(), (dto, valor) -> dto.setEstado(valor));
		
		return modelMapper;
	}
}
