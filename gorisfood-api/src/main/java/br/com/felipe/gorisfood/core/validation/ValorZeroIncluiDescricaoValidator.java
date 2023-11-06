package br.com.felipe.gorisfood.core.validation;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object> {

	private String valorField;
	private String descricaoField;
	private String descricaoObrigatoria;
	
	@Override
	public void initialize(ValorZeroIncluiDescricao constraintAnnotation) {
		this.valorField = constraintAnnotation.valorField();
		this.descricaoField = constraintAnnotation.descricaoField();
		this.descricaoObrigatoria = constraintAnnotation.descricaoObrigatoria();
	}
	
	@Override
	public boolean isValid(Object o, ConstraintValidatorContext context) {
		try {
			var valor = (BigDecimal) BeanUtils.getPropertyDescriptor(o.getClass(), valorField)
								.getReadMethod()
								.invoke(o);
			
			var campoValidar = (String) BeanUtils.getPropertyDescriptor(o.getClass(), descricaoField)
								.getReadMethod()
								.invoke(o);
			
			if(valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && campoValidar != null) {
				return campoValidar.toLowerCase().contains(descricaoObrigatoria.toLowerCase());
			}
			
		} catch (Exception e) {
			throw new ValidationException(e);
		}
				
		return true;
	}

}
