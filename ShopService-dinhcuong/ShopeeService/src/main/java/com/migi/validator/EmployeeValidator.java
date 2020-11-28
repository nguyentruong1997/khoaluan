package com.migi.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.migi.model.EmployeeDTO;
 

@Component 
public class EmployeeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EmployeeDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		EmployeeDTO nv =(EmployeeDTO) target;
		if(nv.getHo()==null || nv.getHo().length() ==0 ) {
			errors.rejectValue("ho","field.required");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "manhanvien", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "diachi", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ten", "field.required");
		/*if(!nv.getLuongcoban().equals(100.0)) {
			errors.rejectValue("luongcoban","luongcoban.invalid");
		}*/
		
		
		
	}
	
	
}