package br.com.felipe.gorisfood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.controller.FormaPagamentoController;
import br.com.felipe.gorisfood.api.model.response.FormaPagamentoResponseDTO;
import br.com.felipe.gorisfood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoResponseDtoAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoResponseDTO> {
	
	private static final Class<FormaPagamentoController> FORMA_PAGAMENTO_CONTROLLER_CLASS = FormaPagamentoController.class;
	
	@Autowired
	private ModelMapper mapper;
	
	public FormaPagamentoResponseDtoAssembler() {
		super(FORMA_PAGAMENTO_CONTROLLER_CLASS, FormaPagamentoResponseDTO.class);
	}
	
	@Override
	public FormaPagamentoResponseDTO toModel(FormaPagamento formaPagamento) {
		var formaPagamentoResponseDTO = createModelWithId(formaPagamento.getId(), formaPagamento);
		mapper.map(formaPagamento, formaPagamentoResponseDTO);
		
		formaPagamentoResponseDTO.add(
			linkTo(
				methodOn(FORMA_PAGAMENTO_CONTROLLER_CLASS)
				.listar(null))
			.withRel("formas-pagamento"));
		
		return formaPagamentoResponseDTO;
	}
	
	@Override
	public CollectionModel<FormaPagamentoResponseDTO> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
		return super.toCollectionModel(entities).add(
			linkTo(
				methodOn(FORMA_PAGAMENTO_CONTROLLER_CLASS)
				.listar(null))
			.withSelfRel());
	}
}
