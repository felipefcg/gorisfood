package br.com.felipe.gorisfood.core.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.SmartValidator;

import br.com.felipe.gorisfood.domain.model.Restaurante;
import lombok.SneakyThrows;

public class ValidacaoUtils {

	@Autowired
	private static SmartValidator validator;
	
	@SneakyThrows
	public static void validate(Restaurante restaurante, String objectName) {
		
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
		validator.validate(restaurante, bindingResult);
		
		if(bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		}
		
	}
}
