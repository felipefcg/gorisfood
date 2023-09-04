package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import br.com.felipe.gorisfood.api.v1.model.request.SenhaRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.request.UsuarioComSenhaRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.request.UsuarioRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.UsuarioResponseDTO;


public interface UsuarioControllerOpenApi {
	public CollectionModel<UsuarioResponseDTO> listar();
	UsuarioResponseDTO buscar(Long id);
	UsuarioResponseDTO criar(UsuarioComSenhaRequestDTO dto);
	UsuarioResponseDTO alterar(Long id, UsuarioRequestDTO dto);
	void alterarSenha(Long id, SenhaRequestDTO dto);
}
