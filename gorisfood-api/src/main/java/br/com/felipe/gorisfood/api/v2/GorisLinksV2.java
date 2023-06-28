package br.com.felipe.gorisfood.api.v2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.controller.CidadeController;

@Component
public class GorisLinksV2 {

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
	
}
