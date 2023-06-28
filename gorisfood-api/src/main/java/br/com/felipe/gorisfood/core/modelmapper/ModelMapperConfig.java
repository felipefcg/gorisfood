package br.com.felipe.gorisfood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.felipe.gorisfood.api.v1.model.request.ItemPedidoResquestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.EnderecoResponseDTO;
import br.com.felipe.gorisfood.api.v2.model.request.CidadeRequestDTOV2;
import br.com.felipe.gorisfood.domain.model.Cidade;
import br.com.felipe.gorisfood.domain.model.Endereco;
import br.com.felipe.gorisfood.domain.model.ItemPedido;

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
		
		modelMapper.createTypeMap(ItemPedidoResquestDTO.class, ItemPedido.class)
			.addMappings( mapper -> {
				mapper.skip(ItemPedido::setId);
				mapper.skip((dest, value) -> dest.getPedido().setId(null) );
			})
		;
		
		modelMapper.createTypeMap(CidadeRequestDTOV2.class, Cidade.class)
			.addMappings( mapper -> mapper.skip(Cidade::setId));
		
		return modelMapper;
	}
	
}
