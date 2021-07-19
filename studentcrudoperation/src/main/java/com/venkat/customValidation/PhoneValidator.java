package com.venkat.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	@Override
	public boolean isValid(String phone, ConstraintValidatorContext context) {
		if (phone == null || phone.matches("[0-9]+") && phone.length() > 9 && phone.length() < 13) {
			return true;
		} else {
			return false;
		}
	}
}

