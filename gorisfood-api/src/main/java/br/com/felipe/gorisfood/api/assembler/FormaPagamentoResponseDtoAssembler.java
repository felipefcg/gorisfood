package br.com.felipe.gorisfood.api.assembler;

import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.response.FormaPagamentoResponseDTO;
import br.com.felipe.gorisfood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoResponseDtoAssembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public FormaPagamentoResponseDTO toDto(FormaPagamento formaPagamento) {
		return mapper.map(formaPagamento, FormaPagamentoResponseDTO.class);
	}
	
	public List<FormaPagamentoResponseDTO> toDtoList (Collection<FormaPagamento> formasPagamento) {
		
		var typeToken = new TypeToken<List<FormaPagamentoResponseDTO>>(){};
		return mapper.map(formasPagamento, typeToken.getType());
	}
}
