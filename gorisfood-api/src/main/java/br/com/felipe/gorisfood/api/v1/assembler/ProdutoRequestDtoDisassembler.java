package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.model.request.ProdutoRequestDTO;
import br.com.felipe.gorisfood.domain.model.Produto;

@Component
public class ProdutoRequestDtoDisassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Produto toModel (ProdutoRequestDTO produtoDTO ) {
		return mapper.map(produtoDTO, Produto.class);
	}

	public void copyDtoToModel(ProdutoRequestDTO produtoDTO, Produto produto) {
		
		mapper.map(produtoDTO, produto);
	}
}
