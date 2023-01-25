package br.com.felipe.gorisfood.api.assembler;

import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.response.PedidoResponseDTO;
import br.com.felipe.gorisfood.api.model.response.PermissaoResponseDTO;
import br.com.felipe.gorisfood.domain.model.Pedido;

@Component
public class PedidoResponseDtoAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public PedidoResponseDTO toDto(Pedido model) {
		return mapper.map(model, PedidoResponseDTO.class);
	}
	
	public List<PedidoResponseDTO> toDtoList(Collection<Pedido> modelList) {
		
		return modelList
				.stream()
				.map(this::toDto)
				.toList();
	}
}
