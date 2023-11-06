package br.com.felipe.gorisfood.core.validation;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

	private List<String> allowed;
	
	@Override
	public void initialize(FileContentType constraintAnnotation) {
		this.allowed = Arrays.asList(constraintAnnotation.allowed());
	}
	
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
			
		return isContentTypeDesejado(value);
	}

	private boolean isContentTypeDesejado(MultipartFile value) {
		
		return value == null ||  allowed.contains(value.getContentType());
	}

}
