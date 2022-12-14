package br.com.felipe.gorisfood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.domain.exception.EntidadeEmUsoExcpetion;
import br.com.felipe.gorisfood.domain.exception.EstadoNaoEncontradoException;
import br.com.felipe.gorisfood.domain.model.Estado;
import br.com.felipe.gorisfood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	private static final String ESTADO_EM_USO = "Estado Bom  %d não pode ser removida pois está em uso.";
	
	@Autowired
	private EstadoRepository repository;
	
	public List<Estado> listar() {
		return repository.findAll();
	}
	
	public Estado buscar(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new EstadoNaoEncontradoException(id));
	}
	
	@Transactional
	public Estado salvar(Estado estado) {
		return repository.save(estado);
	}
	
	@Transactional
	public Estado atualizar(Long id, Estado estado) {
		Estado estadoSalvo = buscar(id);
		
		BeanUtils.copyProperties(estado, estadoSalvo, "id");
		return salvar(estadoSalvo);
	}
	
	@Transactional
	public void remover(Long id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExcpetion(
					String.format(ESTADO_EM_USO, id));
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(id);
		}
	}
}
