package br.com.felipe.gorisfood.api.assembler;

import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.response.PermissaoResponseDTO;
import br.com.felipe.gorisfood.domain.model.Permissao;

@Component
public class PermissaoResponseDtoAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public PermissaoResponseDTO toDto(Permissao model) {
		return mapper.map(model, PermissaoResponseDTO.class);
	}
	
	public List<PermissaoResponseDTO> toDtoList(Collection<Permissao> modelList) {
		var typeToken = new TypeToken<List<PermissaoResponseDTO>>() {};
		return mapper.map(modelList, typeToken.getType());
	}
}
