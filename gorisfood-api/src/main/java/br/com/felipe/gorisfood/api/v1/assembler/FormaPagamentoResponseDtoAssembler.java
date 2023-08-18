package br.com.felipe.gorisfood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.felipe.gorisfood.api.v1.GorisLinks;
import br.com.felipe.gorisfood.api.v1.controller.FormaPagamentoController;
import br.com.felipe.gorisfood.api.v1.model.response.FormaPagamentoResponseDTO;
import br.com.felipe.gorisfood.core.security.AuthUserSecurity;
import br.com.felipe.gorisfood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoResponseDtoAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoResponseDTO> {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private GorisLinks gorisLinks;

	@Autowired
	private AuthUserSecurity authUserSecurity;

	public FormaPagamentoResponseDtoAssembler() {
		super(FormaPagamentoController.class, FormaPagamentoResponseDTO.class);
	}
	
	@Override
	public FormaPagamentoResponseDTO toModel(FormaPagamento formaPagamento) {
		var formaPagamentoResponseDTO = createModelWithId(formaPagamento.getId(), formaPagamento);
		mapper.map(formaPagamento, formaPagamentoResponseDTO);
		
		if (authUserSecurity.podeConsultarFormasPagamento()) {
			formaPagamentoResponseDTO.add(gorisLinks.linkToFormasPagamento("formasPagamento"));
		}
		
		return formaPagamentoResponseDTO;
	}
	
	@Override
	public CollectionModel<FormaPagamentoResponseDTO> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
		var collectionModel = super.toCollectionModel(entities);
		
		if (authUserSecurity.podeConsultarFormasPagamento()) {
			collectionModel.add(gorisLinks.linkToFormasPagamento());
		}
		
		return collectionModel;
	}
	
}
