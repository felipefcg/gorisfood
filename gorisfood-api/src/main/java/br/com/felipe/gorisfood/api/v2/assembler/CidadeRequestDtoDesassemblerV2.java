package br.com.felipe.gorisfood.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v2.model.request.CidadeRequestDTOV2;
import br.com.felipe.gorisfood.domain.model.Cidade;

@Component
public class CidadeRequestDtoDesassemblerV2 {

	@Autowired
	private ModelMapper mapper;
	
	public Cidade toModel(CidadeRequestDTOV2 cidadeDTO) {
		return mapper.map(cidadeDTO, Cidade.class);
	}

	public void copyDtoToModel(CidadeRequestDTOV2 cidadeDTO, Cidade cidadeAtual) {
		/*
		 * Para evitar org.hibernate.HibernateException: identifier of an instance of 
		 * br.com.felipe.gorisfood.domain.model.Cidade was altered from 1 to 3
		 */
		cidadeAtual.setEstado(null);
		mapper.map(cidadeDTO, cidadeAtual);
	}
}
