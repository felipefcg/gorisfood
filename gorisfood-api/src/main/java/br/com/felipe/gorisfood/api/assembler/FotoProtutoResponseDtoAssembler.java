package br.com.felipe.gorisfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.response.FotoProdutoReponseDTO;
import br.com.felipe.gorisfood.domain.model.FotoProduto;

@Component
public class FotoProtutoResponseDtoAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public FotoProdutoReponseDTO toDto(FotoProduto model) {
		return mapper.map(model, FotoProdutoReponseDTO.class);
	}
}
