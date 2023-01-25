package br.com.felipe.gorisfood.api.assembler;

import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.response.PedidoResumidoResponseDTO;
import br.com.felipe.gorisfood.domain.model.Pedido;

@Component
public class PedidoResumidoResponseDtoAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public PedidoResumidoResponseDTO toDto(Pedido model) {
		return mapper.map(model, PedidoResumidoResponseDTO.class);
	}
	
	public List<PedidoResumidoResponseDTO> toDtoList(Collection<Pedido> modelList) {
		
		return modelList
				.stream()
				.map(this::toDto)
				.toList();
	}
}
