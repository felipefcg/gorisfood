package br.com.felipe.gorisfood.core.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class CEPValidator implements ConstraintValidator<CEP, String> {

	private static final String PATTERN_CEP = "^\\d{5}-\\d{3}$";
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if (StringUtils.isBlank(value)) {
			return true;
		}
		
		return Pattern.matches(PATTERN_CEP, value);
		
	}

}
