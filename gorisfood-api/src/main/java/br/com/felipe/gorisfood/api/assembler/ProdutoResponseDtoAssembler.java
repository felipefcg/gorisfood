package br.com.felipe.gorisfood.api.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.response.ProdutoResponseDTO;
import br.com.felipe.gorisfood.domain.model.Produto;

@Component
public class ProdutoResponseDtoAssembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public ProdutoResponseDTO toDto(Produto produto) {
		return mapper.map(produto, ProdutoResponseDTO.class);
	}
	
	public List<ProdutoResponseDTO>	toDtoList(List<Produto> produtos) {
		var tokenType = new TypeToken<List<ProdutoResponseDTO>>() {};
		return mapper.map(produtos,tokenType.getType());
	}
}
