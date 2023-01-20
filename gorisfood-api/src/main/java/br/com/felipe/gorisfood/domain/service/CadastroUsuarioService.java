package br.com.felipe.gorisfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.domain.exception.SenhaInvalidaExcpetion;
import br.com.felipe.gorisfood.domain.exception.UsuarioNaoEncontradoException;
import br.com.felipe.gorisfood.domain.model.Usuario;
import br.com.felipe.gorisfood.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

	private static final String SENHA_NAO_CONRRESPONDENTE = "Senha atual informada não coincide com a senha do usuário";
	@Autowired
	private UsuarioRepository repository;
	
	@Transactional
	public List<Usuario> listar() {
		return repository.findAll();
	}
	
	@Transactional
	public Usuario buscar(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new UsuarioNaoEncontradoException(id));
	}
	
	@Transactional
	public Usuario criar(Usuario usuario) {
		return repository.save(usuario);
	}
	
	@Transactional
	public Usuario alterar(Usuario usuario) {
		return criar(usuario);
	}
	
	@Transactional
	public void alterarSenha(Usuario usuario, String senhaAntiga, String senhaNova) {
		
		if(usuario.senhaCoincide(senhaAntiga)) {
			throw new SenhaInvalidaExcpetion(SENHA_NAO_CONRRESPONDENTE);
		}
		
		usuario.setSenha(senhaNova);
		
		repository.save(usuario);
	}

}
