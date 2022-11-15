package br.com.felipe.gorisfood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.felipe.gorisfood.domain.exception.EntidadeEmUsoExcpetion;
import br.com.felipe.gorisfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.felipe.gorisfood.domain.exception.EntidadeRelacionamentoNaoEncontradaException;
import br.com.felipe.gorisfood.domain.model.Cidade;
import br.com.felipe.gorisfood.domain.model.Estado;
import br.com.felipe.gorisfood.domain.repository.CidadeRepository;
import br.com.felipe.gorisfood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	private static final String MSG_ESTADO_NAO_ENCONTRADO = "Não foi encontrado Estado com o id %d";

	private static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida pois está em uso.";

	private static final String MSG_CIDADE_ENCONTRADA = "Cidade com %d não encontrada";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	public Cidade buscar(Long id) {
		return cidadeRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(MSG_CIDADE_ENCONTRADA, id)));
	}

	public Cidade salvar(Cidade cidade) {
		Long idEstado = cidade.getEstado().getId();
		
		Estado estado = buscarEstado(idEstado);
		
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}

	public Cidade alterar (Long id, Cidade cidade) {
		Cidade cidadeAtual = buscar(id);
		Estado estado = buscarEstado(cidade.getEstado().getId());
		
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		cidadeAtual.setEstado(estado);
		
		return cidadeRepository.save(cidadeAtual);
	}
	
	public void remover(Long id) {
		try {
			cidadeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_ENCONTRADA, id));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExcpetion(
					String.format(MSG_CIDADE_EM_USO, id));
		}
	}
	
	private Estado buscarEstado(Long idEstado) {
		return estadoRepository.findById(idEstado)
				.orElseThrow(() -> new EntidadeRelacionamentoNaoEncontradaException(
						String.format(MSG_ESTADO_NAO_ENCONTRADO, idEstado)));
	}
}
