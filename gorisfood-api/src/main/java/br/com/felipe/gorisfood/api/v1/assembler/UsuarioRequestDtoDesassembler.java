package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.model.request.UsuarioComSenhaRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.request.UsuarioRequestDTO;
import br.com.felipe.gorisfood.domain.model.Usuario;

@Component
public class UsuarioRequestDtoDesassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Usuario toModel(UsuarioComSenhaRequestDTO dto) {
		return mapper.map(dto, Usuario.class);
	}
	
	public void copyDtoToModel(UsuarioRequestDTO dto, Usuario model) {
		mapper.map(dto, model);
	}
}
