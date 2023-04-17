package br.com.felipe.gorisfood.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.controller.CidadeController;
import br.com.felipe.gorisfood.api.controller.CozinhaController;
import br.com.felipe.gorisfood.api.controller.EstadoController;
import br.com.felipe.gorisfood.api.controller.FormaPagamentoController;
import br.com.felipe.gorisfood.api.controller.PedidoController;
import br.com.felipe.gorisfood.api.controller.RestauranteController;
import br.com.felipe.gorisfood.api.controller.RestauranteProdutoContoller;
import br.com.felipe.gorisfood.api.controller.RestauranteUsuarioResponsavelController;
import br.com.felipe.gorisfood.api.controller.UsuarioController;
import br.com.felipe.gorisfood.api.controller.UsuarioGrupoController;

@Component
public class GorisLinks {

	public static final TemplateVariables PAGE_VARIABLES = new TemplateVariables(
																new TemplateVariable("pagina", VariableType.REQUEST_PARAM),
																new TemplateVariable("tamanhoPagina", VariableType.REQUEST_PARAM),
																new TemplateVariable("sort", VariableType.REQUEST_PARAM));
	
	//Cidades
	public Link linkToCidades() {
		return linkTo(CidadeController.class)
			  .withSelfRel();
	}

	public Link linkToCidades(String rel) {
		return linkTo(CidadeController.class)
			  .withRel(rel);
	}
	
	public Link linkToCidade(Long cidadeId) {
		return linkTo(methodOn(CidadeController.class).buscar(cidadeId))
			   .withSelfRel();
	}
	
	public Link linkToCidade(Long cidadeId, String rel) {
		return linkTo(methodOn(CidadeController.class).buscar(cidadeId))
			   .withRel(rel);
	}	
	
	//Cozinhas
	public Link linkToCozinhas() {
		return linkTo(CozinhaController.class)
				.withSelfRel();
	}
	
	public Link linkToCozinhas(String rel) {
		return linkTo(CozinhaController.class)
				.withRel(rel);
	}
	
	//Estados
	public Link linkToEstados() {
		return linkTo(EstadoController.class)
				.withSelfRel();
	}
	
	public Link linkToEstados(String rel) {
		return linkTo(EstadoController.class)
				.withRel(rel);
	}
	
	public Link linkToEstado(Long estadoId) {
		return linkTo(methodOn(EstadoController.class).buscar(estadoId))
			   .withSelfRel();
	}
	
	public Link linkToEstado(Long estadoId, String rel) {
		return linkTo(methodOn(EstadoController.class).buscar(estadoId))
			   .withRel(rel);
	}	
	
	//Formas Pagamento
	public Link linkToFormasPagamento() {
		return linkTo(FormaPagamentoController.class)
			   .withSelfRel();
	}
	
	public Link linkToFormasPagamento(String rel) {
		return linkTo(FormaPagamentoController.class)
			   .withRel(rel);
	}
	
	public Link linkToFormaPagamento(Long formaPagamentoId) {
		return linkTo(methodOn(FormaPagamentoController.class).buscar(formaPagamentoId, null))
			   .withSelfRel();
	}
	
	public Link linkToFormaPagamento(Long formaPagamentoId, String rel) {
		return linkTo(methodOn(FormaPagamentoController.class).buscar(formaPagamentoId, null))
			   .withRel(rel);
	}
	
	//Grupos Usuario
	public Link linkToGruposUsuario(Long usuarioId, String rel) {
	    return linkTo(methodOn(UsuarioGrupoController.class)
	            .listar(usuarioId)).withRel(rel);
	}

	public Link linkToGruposUsuario(Long usuarioId) {
	    return linkToGruposUsuario(usuarioId, IanaLinkRelations.SELF.value());
	}
	
	//Pedidos
	public Link linkToPedidos() {
		
		var filtroVariables = new TemplateVariables(
				new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));
		
		var pedidosUrl = linkTo(PedidoController.class).toUri().toString();
		
		return Link.of(UriTemplate.of(pedidosUrl, filtroVariables.concat(PAGE_VARIABLES)), "pedidos");
	}
	
	//Produtos
	public Link linkToProduto(Long produtoId, Long restauranteId) {
		return linkTo(methodOn(RestauranteProdutoContoller.class).buscar(restauranteId, produtoId))
			  .withSelfRel();
	}
	
	public Link linkToProduto(Long produtoId, Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteProdutoContoller.class).buscar(restauranteId, produtoId))
			  .withRel(rel);
	}
	
	//Responsaveis Restaurante
	public Link linkToResponsaveisRestaurante(Long restauranteId, String rel) {
	    return linkTo(methodOn(RestauranteUsuarioResponsavelController.class)
	            .listar(restauranteId)).withRel(rel);
	}

	public Link linkToResponsaveisRestaurante(Long restauranteId) {
	    return linkToResponsaveisRestaurante(restauranteId, IanaLinkRelations.SELF.value());
	}
	
	//Restaurantes
	public Link linkToRestaurante(Long restauranteId) {
		return linkTo(methodOn(RestauranteController.class).buscar(restauranteId))
			   .withSelfRel();
	}
	
	public Link linkToRestaurante(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).buscar(restauranteId))
			   .withRel(rel);
	}
	
	//Usuarios
	public Link linkToUsuario(Long usuarioId) {
		return linkTo(methodOn(UsuarioController.class).buscar(usuarioId))
			   .withSelfRel();
	}
	
	public Link linkToUsuario(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioController.class).buscar(usuarioId))
			   .withRel(rel);
	}
	
	public Link linkToUsuarios() {
		return linkTo(UsuarioController.class)
			   .withSelfRel();
	}
	
	public Link linkToUsuarios(String rel) {
		return linkTo(UsuarioController.class)
			   .withRel(rel);
	}
	
	
	
}
