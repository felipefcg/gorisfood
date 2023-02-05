package br.com.felipe.gorisfood.core.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.felipe.gorisfood.api.model.request.ItemPedidoResquestDTO;
import br.com.felipe.gorisfood.api.model.response.EnderecoResponseDTO;
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

//		modelMapper.typeMap(PedidoRequestDTO.class, Pedido.class)
//			.<List<ItemPedido>>addMapping( 
//					src -> src.getItens(), 
//					(dest, v) -> dest.setItens(v) )
//			
//		;
		
		modelMapper.createTypeMap(ItemPedidoResquestDTO.class, ItemPedido.class)
			.addMappings( mapper -> {
				mapper.skip(ItemPedido::setId);
				mapper.skip((dest, value) -> dest.getPedido().setId(null) );
			})
		;
		return modelMapper;
	}
	
}
