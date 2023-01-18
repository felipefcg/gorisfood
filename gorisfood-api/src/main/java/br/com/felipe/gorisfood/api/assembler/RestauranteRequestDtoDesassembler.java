package br.com.felipe.gorisfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.request.RestauranteRequestDTO;
import br.com.felipe.gorisfood.domain.model.Cidade;
import br.com.felipe.gorisfood.domain.model.Cozinha;
import br.com.felipe.gorisfood.domain.model.Restaurante;

@Component
public class RestauranteRequestDtoDesassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurante toDomain(RestauranteRequestDTO restauranteDTO) {
		return modelMapper.map(restauranteDTO, Restaurante.class);
	}
	
	public void copyToDomain(RestauranteRequestDTO restauranteDTO, Restaurante restaurante) {
		/*
		 * Para evitar org.hibernate.HibernateException: identifier of an instance of 
		 * br.com.felipe.gorisfood.domain.model.Cozinha was altered from 1 to 3
		 */
		restaurante.setCozinha(Cozinha.builder().build());
		restaurante.getEndereco().setCidade(new Cidade());
		
		modelMapper.map(restauranteDTO, restaurante);
	}
}
