package br.com.felipe.gorisfood.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipe.gorisfood.domain.exception.RestauranteNaoEncontradoException;
import br.com.felipe.gorisfood.domain.model.Cozinha;
import br.com.felipe.gorisfood.domain.model.Restaurante;
import br.com.felipe.gorisfood.domain.repository.RestauranteRepository;


@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cozinhaService;
	
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}
	
	public Restaurante buscar(Long id) {
		
		 return restauranteRepository.findById(id)
				 .orElseThrow(() -> new RestauranteNaoEncontradoException(id));
	}

	public Restaurante criar(Restaurante restaurante) {
		colocarCozinhaNoRestaurante(restaurante);
		return restauranteRepository.save(restaurante);
	}

	public Restaurante alterar(Long id, Restaurante restaurante) {
		Restaurante restauranteSalvo = buscar(id);
		colocarCozinhaNoRestaurante(restaurante);
		BeanUtils.copyProperties(restaurante, restauranteSalvo, 
				"id", "formasPagamento", "endereco", "dataCadastro", "produtos");
		
		return restauranteRepository.save(restauranteSalvo);
	}

	public Restaurante alterarParcialmente(Restaurante restauranteAtualizado) {
		colocarCozinhaNoRestaurante(restauranteAtualizado);
		
		return restauranteRepository.save(restauranteAtualizado);
	}
	
	private void colocarCozinhaNoRestaurante(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinhaSalva = cozinhaService.buscar(cozinhaId);
		
		restaurante.setCozinha(cozinhaSalva);
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
}
