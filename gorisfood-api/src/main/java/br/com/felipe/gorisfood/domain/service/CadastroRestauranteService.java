package br.com.felipe.gorisfood.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.domain.exception.RestauranteNaoEncontradoException;
import br.com.felipe.gorisfood.domain.model.Cozinha;
import br.com.felipe.gorisfood.domain.model.FormaPagamento;
import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.model.Usuario;
import br.com.felipe.gorisfood.domain.repository.RestauranteRepository;


@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cozinhaService;
	
	@Autowired
	private CadastroCidadeService cidadeService;
	
	@Autowired
	private CadastroFormaPagamentoService formaPagamentoService;
	
	@Autowired
	private CadastroUsuarioService usuarioService;
	
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}
	
	public Restaurante buscar(Long id) {
		
		 return restauranteRepository.findById(id)
				 .orElseThrow(() -> new RestauranteNaoEncontradoException(id));
	}

	@Transactional
	public Restaurante criar(Restaurante restaurante) {
		colocarCozinhaNoRestaurante(restaurante);
		colocarCidadeNoRestaurante(restaurante);
		return restauranteRepository.save(restaurante);
	}

	@Transactional
	public Restaurante alterar(Restaurante restaurante) {
		return criar(restaurante);
	}

	@Transactional
	public Restaurante alterarParcialmente(Restaurante restauranteAtualizado) {
		colocarCozinhaNoRestaurante(restauranteAtualizado);
		colocarCidadeNoRestaurante(restauranteAtualizado);
		return restauranteRepository.save(restauranteAtualizado);
	}
	
	private void colocarCozinhaNoRestaurante(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinhaSalva = cozinhaService.buscar(cozinhaId);
		
		restaurante.setCozinha(cozinhaSalva);
	}
	
	private void colocarCidadeNoRestaurante(Restaurante restaurante) {
		var cidadeId = restaurante.getEndereco().getCidade().getId();
		var cidade = cidadeService.buscar(cidadeId);
			
		restaurante.getEndereco().setCidade(cidade);
	}
	
	public List<Restaurante> buscarPorNomeECozinha(String nome, Long cozinhaId) {
		return restauranteRepository.consultaPorNome(nome, cozinhaId);
	}
	
	public List<Restaurante> buscarPorNomeETaxa(String nome, BigDecimal taxaInicio, BigDecimal taxaFinal) {
		return restauranteRepository.consultaPorNomeETaxaFrete(nome, taxaInicio, taxaFinal);
	}
	
	public List<Restaurante> buscarPorCozinhaETaxa(String nome, BigDecimal taxaInicio, BigDecimal taxaFinal) {
		return restauranteRepository.consultarPorCozinhaETaxa(nome, taxaInicio, taxaFinal);
	}
	
	public List<Restaurante> buscarPorFreteGratisENome(String nome) {
		return restauranteRepository.consultarFreteGratis(nome);
	}

	public Optional<Restaurante> buscarPrimeiro() {
		return restauranteRepository.buscarPrimeiro();
	}

	public void ativar(Long id) {
		var restaurante = buscar(id);
		restaurante.ativar();
		restauranteRepository.save(restaurante);
	}
	
	public void inativar(Long id) {
		var restaurante = buscar(id);
		restaurante.inativar();
		restauranteRepository.save(restaurante);
	}

	@Transactional
	public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		var restaurante = buscar(restauranteId);
		var formaPagamento = formaPagamentoService.buscar(formaPagamentoId);
		restaurante.adicionarFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		var restaurante = buscar(restauranteId);
		var formaPagamento = formaPagamentoService.buscar(formaPagamentoId);
		restaurante.removerFormaPagamento(formaPagamento);
	}

	@Transactional
	public Set<FormaPagamento> listarFormasPagamentoDoRestaurante(Long restauranteId) {
		var restaurante = buscar(restauranteId);
		return restaurante.getFormasPagamento();
	}
	
	@Transactional
	public void abrir(Long id) {
		var restaurante = buscar(id);
		restaurante.abrir();
	}
	
	@Transactional
	public void fechar (Long id) {
		var restaurante = buscar(id);
		restaurante.fechar();
	}
	
	@Transactional
	public void associarUsuarioResponsavel(Long restauranteId, Long usuarioId) {
		var restaurante = buscar(restauranteId);
		var usuario = usuarioService.buscar(usuarioId);
		
		restaurante.adicionarResponsavel(usuario);
	}
	
	@Transactional
	public void desassociarUsuarioResponsavel(Long restauranteId, Long usuarioId) {
		var restaurante = buscar(restauranteId);
		var usuario = new Usuario();
		usuario.setId(usuarioId);
		
		restaurante.removerResponsavel(usuario);
	}
	
}
