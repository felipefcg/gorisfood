package br.com.felipe.gorisfood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.felipe.gorisfood.domain.exception.EntidadeEmUsoExcpetion;
import br.com.felipe.gorisfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.model.Estado;
import br.com.felipe.gorisfood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	@Autowired
	private EstadoRepository repository;
	
	public List<Estado> listar() {
		return repository.listar();
	}
	
	public Estado buscar(Long id) {
		
		try {
			return repository.buscar(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Estado não encontrado com o id %s", id));
		}
	}
	
	public Estado salvar(Estado estado) {
		return repository.salvar(estado);
	}
	
	public Estado atualizar(Long id, Estado estado) {
		Estado estadoSalvo = buscar(id);
		
		BeanUtils.copyProperties(estado, estadoSalvo, "id");
		return salvar(estadoSalvo);
	}
	
	public void remover(Long id) {
		try {
			repository.remover(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExcpetion(
					String.format("Cozinha de estado %d não pode ser removida pois está em uso.", id));
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Estado não encontrado com o id %s", id));
		}
	}
}
