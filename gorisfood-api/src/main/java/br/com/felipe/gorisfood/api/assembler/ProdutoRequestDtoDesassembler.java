package br.com.felipe.gorisfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.request.ProdutoRequestDTO;
import br.com.felipe.gorisfood.domain.model.Produto;

@Component
public class ProdutoRequestDtoDesassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Produto toModel (ProdutoRequestDTO produtoDTO ) {
		return mapper.map(produtoDTO, Produto.class);
	}

	public void copyDtoToModel(ProdutoRequestDTO produtoDTO, Produto produto) {
		/*
		 * Para evitar org.hibernate.HibernateException: identifier of an instance of 
		 * br.com.felipe.gorisfood.domain.model.Restaurante was altered from 1 to 5
		 */
		produto.setRestaurante(null);
		mapper.map(produtoDTO, produto);
	}
}
