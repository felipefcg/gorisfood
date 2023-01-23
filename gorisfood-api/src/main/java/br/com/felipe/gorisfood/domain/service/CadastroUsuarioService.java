package br.com.felipe.gorisfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.felipe.gorisfood.domain.exception.NegocioException;
import br.com.felipe.gorisfood.domain.exception.SenhaInvalidaExcpetion;
import br.com.felipe.gorisfood.domain.exception.UsuarioNaoEncontradoException;
import br.com.felipe.gorisfood.domain.model.Grupo;
import br.com.felipe.gorisfood.domain.model.Usuario;
import br.com.felipe.gorisfood.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

	private static final String SENHA_NAO_CONRRESPONDENTE = "Senha atual informada não coincide com a senha do usuário";
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private CadastroGupoService grupoService;
	
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
		repository.detached(usuario);
		
		var optionalUsuarioCadastrado = repository.findByEmail(usuario.getEmail());
		
		if(optionalUsuarioCadastrado.isPresent() && !usuario.equals(optionalUsuarioCadastrado.get())) {
			throw new NegocioException(String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
		}
		
		return repository.save(usuario);
	}
	
	@Transactional
	public Usuario alterar(Usuario usuario) {
		return criar(usuario);
	}
	
	@Transactional
	public void alterarSenha(Usuario usuario, String senhaAntiga, String senhaNova) {
		
		if(usuario.senhaNaoCoincide(senhaAntiga)) {
			throw new SenhaInvalidaExcpetion(SENHA_NAO_CONRRESPONDENTE);
		}
		
		usuario.setSenha(senhaNova);
		
		repository.save(usuario);
	}
	
	@Transactional
	public void adicionarGrupo(Long usuarioId, Long grupoId) {
		var usuario = buscar(usuarioId);
		var grupo = grupoService.buscar(grupoId);
		
		usuario.adicionarGrupo(grupo);
	}
	
	@Transactional
	public void removerGrupo(Long usuarioId, Long grupoId) {
		var usuario = buscar(usuarioId);
		var grupo = new Grupo();
		grupo.setId(grupoId);
		
		usuario.removerGrupo(grupo);
	}

}
