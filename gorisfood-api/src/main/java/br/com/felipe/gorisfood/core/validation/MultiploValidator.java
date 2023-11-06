package br.com.felipe.gorisfood.core.validation;

import java.math.BigDecimal;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number> {

	private int numeroMultiplo;
	
	@Override
	public void initialize(Multiplo constraintAnnotation) {
		this.numeroMultiplo = constraintAnnotation.numero();
	}
	
	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) {
		
		if(value != null) {
			var dividendo = BigDecimal.valueOf(value.doubleValue());
			var divisor = BigDecimal.valueOf(numeroMultiplo);
			var resto = dividendo.remainder(divisor);
			
			return BigDecimal.ZERO.compareTo(resto) == 0;
		}
			
		return true;
	}

}
