package br.com.felipe.gorisfood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.felipe.gorisfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.EntidadeRelacionamentoNaoEncontradaException;
import br.com.felipe.gorisfood.domain.model.Cidade;
import br.com.felipe.gorisfood.domain.model.Estado;
import br.com.felipe.gorisfood.domain.repository.CidadeRepository;
import br.com.felipe.gorisfood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Cidade> listar() {
		return cidadeRepository.listar();
	}

	public Cidade buscar(Long id) {
		try {
			return cidadeRepository.buscar(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade com %d não encontrada", id));
		}
	}

	public Cidade salvar(Cidade cidade) {
		Long idEstado = cidade.getEstado().getId();
		
		Estado estado = buscarEstado(idEstado);
		
		cidade.setEstado(estado);
		return cidadeRepository.salvar(cidade);
	}

	public Cidade alterar (Long id, Cidade cidade) {
		Cidade cidadeAtual = buscar(id);
		Estado estado = buscarEstado(cidade.getEstado().getId());
		
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		cidadeAtual.setEstado(estado);
		
		return cidadeRepository.salvar(cidadeAtual);
	}
	
	public void remover(Long id) {
		try {
			cidadeRepository.remover(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade com %d não encontrada", id));
		}
	}
	
	private Estado buscarEstado(Long idEstado) {
		try {
			return estadoRepository.buscar(idEstado);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeRelacionamentoNaoEncontradaException(
					String.format("Não foi encontrado Estado com o id %d", idEstado));
		}
	}
}
