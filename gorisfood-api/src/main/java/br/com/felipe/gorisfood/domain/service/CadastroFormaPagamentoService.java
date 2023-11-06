package br.com.felipe.gorisfood.domain.service;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.felipe.gorisfood.domain.exception.EntidadeEmUsoExcpetion;
import br.com.felipe.gorisfood.domain.exception.FormaPagamentoNaoEncontradaException;
import br.com.felipe.gorisfood.domain.model.FormaPagamento;
import br.com.felipe.gorisfood.domain.repository.FormaPagamentoRepository;

@Service
public class CadastroFormaPagamentoService {

	private static final String FORMA_DE_PAGAMENTO_EM_USO = "Forma de Pagamento %d não pode ser removida pois está em uso.";
	
	@Autowired
	private FormaPagamentoRepository repository;
	
	@Transactional
	public List<FormaPagamento> listar() {
		return repository.findAll();
	}

	@Transactional
	public FormaPagamento buscar(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new FormaPagamentoNaoEncontradaException(id));
	}
	
	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return repository.save(formaPagamento);
	}

	@Transactional
	public FormaPagamento alterar(FormaPagamento formaPagamento) {
		return salvar(formaPagamento);
	}
	
	@Transactional
	public void excluir(Long id) {
		try {
			repository.deleteById(id);
			repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExcpetion(String.format(FORMA_DE_PAGAMENTO_EM_USO, id));
		} catch (EmptyResultDataAccessException e) {
			throw new FormaPagamentoNaoEncontradaException(id);
		}
	}
}
