package br.com.felipe.gorisfood.api.core.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.PositiveOrZero.List;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD, PARAMETER, CONSTRUCTOR, ANNOTATION_TYPE, TYPE_USE })
@Constraint(validatedBy = { })
@PositiveOrZero
public @interface TaxaFrete {
	
	@OverridesAttribute(constraint = PositiveOrZero.class, name = "message")
	String message() default "{TaxaFrete.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
