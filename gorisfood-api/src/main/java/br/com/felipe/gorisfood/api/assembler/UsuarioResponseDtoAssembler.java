package br.com.felipe.gorisfood.api.assembler;

import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.model.response.UsuarioResponseDTO;
import br.com.felipe.gorisfood.domain.model.Usuario;

@Component
public class UsuarioResponseDtoAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public UsuarioResponseDTO toDto(Usuario model) {
		return mapper.map(model, UsuarioResponseDTO.class);
	}
	
	public List<UsuarioResponseDTO> toDtoList(Collection<Usuario> modelList) {
		return modelList.stream()
					.map(this::toDto)
					.toList();
	}
}
