package br.com.felipe.gorisfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.domain.exception.GrupoNaoEncontradoException;
import br.com.felipe.gorisfood.domain.model.Grupo;
import br.com.felipe.gorisfood.domain.repository.GrupoRepository;

@Service
public class CadastroGupoService {
	
	@Autowired
	private GrupoRepository repository;
	
	@Transactional
	public List<Grupo> listar(){
		return repository.findAll();
	}
	
	@Transactional
	public Grupo buscar(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new GrupoNaoEncontradoException(id));
	}
	
	@Transactional
	public void excluir(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(id);
		}
	}
	
	@Transactional
	public Grupo salvar(Grupo grupo) {
		return repository.save(grupo);
	}
	
	@Transactional
	public Grupo alterar(Grupo grupo) {
		return salvar(grupo);
	}
}
