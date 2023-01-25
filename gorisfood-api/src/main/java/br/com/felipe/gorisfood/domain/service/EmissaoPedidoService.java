package br.com.felipe.gorisfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipe.gorisfood.domain.exception.PedidoNaoEncontradoException;
import br.com.felipe.gorisfood.domain.model.Pedido;
import br.com.felipe.gorisfood.domain.repository.PedidoRepository;

@Service
public class EmissaoPedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<Pedido> listar(){
		return pedidoRepository.findAll();
	}
	
	public Pedido buscar(Long id) {
		return pedidoRepository.findById(id)
				.orElseThrow(() -> new PedidoNaoEncontradoException(id));
	}
}
