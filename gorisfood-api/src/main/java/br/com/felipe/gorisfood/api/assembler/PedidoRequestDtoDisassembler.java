package br.com.felipe.gorisfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.request.PedidoRequestDTO;
import br.com.felipe.gorisfood.domain.model.Pedido;

@Component
public class PedidoRequestDtoDisassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Pedido toModel(PedidoRequestDTO dto) {
		return mapper.map(dto, Pedido.class);
	}
}
