package br.com.felipe.gorisfood.jpa.formapagamento;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.felipe.gorisfood.GorisfoodApiApplication;
import br.com.felipe.gorisfood.domain.model.FormaPagamento;
import br.com.felipe.gorisfood.domain.repository.FormaPagamentoRepository;

public class AlteracaoFormaPagamento {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(GorisfoodApiApplication.class)
										.web(WebApplicationType.NONE)
										.run(args);
		
		FormaPagamentoRepository repository = context.getBean(FormaPagamentoRepository.class);
	
		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setId(3L);
		formaPagamento.setDescricao("Dinheiro");
		
		formaPagamento = repository.salvar(formaPagamento);
		
		System.out.println(formaPagamento);
	}

}
