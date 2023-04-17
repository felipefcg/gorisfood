package br.com.felipe.gorisfood.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.controller.PedidoController;

import org.springframework.hateoas.TemplateVariable.VariableType;

@Component
public class GorisLinks {

	public static final TemplateVariables PAGE_VARIABLES = new TemplateVariables(
																new TemplateVariable("pagina", VariableType.REQUEST_PARAM),
																new TemplateVariable("tamanhoPagina", VariableType.REQUEST_PARAM),
																new TemplateVariable("sort", VariableType.REQUEST_PARAM));
	public Link linkToPedidos() {
		
		var filtroVariables = new TemplateVariables(
				new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));
		
		var pedidosUrl = linkTo(PedidoController.class).toUri().toString();
		
		return Link.of(UriTemplate.of(pedidosUrl, filtroVariables.concat(PAGE_VARIABLES)), "pedidos");
	}
}
