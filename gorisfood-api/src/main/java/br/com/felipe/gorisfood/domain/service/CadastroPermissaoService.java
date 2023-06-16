package br.com.felipe.gorisfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipe.gorisfood.domain.exception.PermissaoNaoEncontradaException;
import br.com.felipe.gorisfood.domain.model.Permissao;
import br.com.felipe.gorisfood.domain.repository.PermissaoRepository;

@Service
public class CadastroPermissaoService {

	@Autowired
	private PermissaoRepository repository;
	
	public Permissao buscar(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new PermissaoNaoEncontradaException(id));
	}

	public List<Permissao> buscar() {
		return repository.findAll();
	}
}
