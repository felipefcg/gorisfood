package br.com.felipe.gorisfood.api.core.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = {ValorZeroIncluiDescricaoValidator.class})
public @interface ValorZeroIncluiDescricao {

	String valorField();

	String descricaoField();

	String descricaoObrigatoria();
	
	String message() default "valor inválido";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
