package br.com.felipe.gorisfood.jpa.formapagamento;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.felipe.gorisfood.GorisfoodApiApplication;
import br.com.felipe.gorisfood.domain.repository.FormaPagamentoRepository;

public class BuscaFormaPagamento {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(GorisfoodApiApplication.class)
										.web(WebApplicationType.NONE)
										.run(args);
		
		FormaPagamentoRepository repository = context.getBean(FormaPagamentoRepository.class);
		
		repository.listar().forEach(System.out::println);
	}

}
