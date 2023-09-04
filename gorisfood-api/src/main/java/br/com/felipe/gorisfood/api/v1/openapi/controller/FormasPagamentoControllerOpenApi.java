package br.com.felipe.gorisfood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.ServletWebRequest;

import br.com.felipe.gorisfood.api.v1.model.request.FormaPagamentoRequestDTO;
import br.com.felipe.gorisfood.api.v1.model.response.FormaPagamentoResponseDTO;


public interface FormasPagamentoControllerOpenApi {
	ResponseEntity<CollectionModel<FormaPagamentoResponseDTO>> listar(ServletWebRequest request);
	ResponseEntity<FormaPagamentoResponseDTO> buscar(Long id, ServletWebRequest request);
	FormaPagamentoResponseDTO criar(FormaPagamentoRequestDTO formaPagamentoDTO);
	FormaPagamentoResponseDTO alterar(Long id, FormaPagamentoRequestDTO formaPagamentoDTO);
	void remover(@PathVariable Long id);
}
