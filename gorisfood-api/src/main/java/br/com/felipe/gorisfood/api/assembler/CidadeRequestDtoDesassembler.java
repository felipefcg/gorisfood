package br.com.felipe.gorisfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.request.CidadeRequestDTO;
import br.com.felipe.gorisfood.domain.model.Cidade;

@Component
public class CidadeRequestDtoDesassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Cidade toModel(CidadeRequestDTO cidadeDTO) {
		return mapper.map(cidadeDTO, Cidade.class);
	}

	public void copyDtoToModel(CidadeRequestDTO cidadeDTO, Cidade cidadeAtual) {
		/*
		 * Para evitar org.hibernate.HibernateException: identifier of an instance of 
		 * br.com.felipe.gorisfood.domain.model.Cidade was altered from 1 to 3
		 */
		cidadeAtual.setEstado(null);
		mapper.map(cidadeDTO, cidadeAtual);
	}
}
