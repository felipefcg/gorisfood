package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.model.request.FormaPagamentoRequestDTO;
import br.com.felipe.gorisfood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoRequestDtoDesassembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public FormaPagamento toModel(FormaPagamentoRequestDTO formaPagamentoDTO) {
		return mapper.map(formaPagamentoDTO, FormaPagamento.class);
	}

	public void copyDtoToModel(FormaPagamentoRequestDTO formaPagamentoDTO, FormaPagamento formaPagamento) {
		mapper.map(formaPagamentoDTO, formaPagamento);
	}
}
