package br.com.felipe.gorisfood.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface Cozinha {
		
		@PreAuthorize("@authUserSecurity.podeConsultarCozinhas()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_COZINHAS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar {}
	}
	
	public @interface Restaurante {
		
		@PreAuthorize("@authUserSecurity.podeConsultarRestaurantes()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}
		
		@PreAuthorize("@authUserSecurity.podeGerenciarCadastroRestaurantes()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarCadastro {}
		
		@PreAuthorize("@authUserSecurity.podeGerenciarFuncionamentoRestaurantes(#id)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarFuncionamento {}
	}
	
	public @interface Pedidos {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeCriar {}
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS') or "
				+ "@authUserSecurity.usuarioAutenticadoIgual(#pedidoFilter.clienteId) or "
				+ "@authUserSecurity.gerenciaRestaurante(returnObject.restaurante.id)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeBuscar {}
		
		@PreAuthorize("@authUserSecurity.podePesquisarPedidos(#pedidoFilter.clienteId, #pedidoFilter.restauranteId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodePesquisar {}
		
		@PreAuthorize("@authUserSecurity.podeGerenciarPedido(#codigoPedido)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeGerenciarPedido {}
	}
	
	public @interface FormasPagamento {
		
		@PreAuthorize("@authUserSecurity.podeConsultarFormasPagamento()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and "
				+ "hasAuthority('EDITAR_FORMAS_PAGAMENTO')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar {}
		
		
	}
	
	public @interface Estados {
		
		@PreAuthorize("@authUserSecurity.podeConsultarEstados()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and "
				+ "hasAuthority('EDITAR_ESTADOS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar {}
		
		
	}

	public @interface Cidades {
	
		@PreAuthorize("@authUserSecurity.podeConsultarCidades()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and "
				+ "hasAuthority('EDITAR_CIDADES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar {}
	
	
	}
	
	public @interface UsuariosGruposPermissoes {
		
		
		@PreAuthorize("@authUserSecurity.podeConsultarUsuariosGruposPermissoes()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}
		
		@PreAuthorize("@authUserSecurity.podeEditarUsuariosGruposPermissoes()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar {}

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and "
				+ "( hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') or "
				+ "@authUserSecurity.usuarioAutenticadoIgual(#pedidoFilter.clienteId) )")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarUsuario {}
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and "
				+ "@authUserSecurity.usuarioAutenticadoIgual(#pedidoFilter.clienteId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarPropriaSenha {}
	}
	
	public @interface Estatisticas {
		
		@PreAuthorize("@authUserSecurity.podeConsultarEstatisticas()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}
	}
	
}
