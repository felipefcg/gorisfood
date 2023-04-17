package br.com.felipe.gorisfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.GorisLinks;
import br.com.felipe.gorisfood.api.controller.FormaPagamentoController;
import br.com.felipe.gorisfood.api.model.response.FormaPagamentoResponseDTO;
import br.com.felipe.gorisfood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoResponseDtoAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoResponseDTO> {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private GorisLinks gorisLinks;
	
	public FormaPagamentoResponseDtoAssembler() {
		super(FormaPagamentoController.class, FormaPagamentoResponseDTO.class);
	}
	
	@Override
	public FormaPagamentoResponseDTO toModel(FormaPagamento formaPagamento) {
		var formaPagamentoResponseDTO = createModelWithId(formaPagamento.getId(), formaPagamento);
		mapper.map(formaPagamento, formaPagamentoResponseDTO);
		
		formaPagamentoResponseDTO.add(gorisLinks.linkToFormasPagamento("formas-pagamento"));
		
		return formaPagamentoResponseDTO;
	}
	
	@Override
	public CollectionModel<FormaPagamentoResponseDTO> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
		return super.toCollectionModel(entities).add(gorisLinks.linkToFormasPagamento());
	}
}
