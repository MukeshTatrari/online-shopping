package com.mukesh.onlineshopping.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mukesh.shoppingbackend.dto.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Product product = (Product) target;

		// check file have been selected or not

		if (product.getFile() == null || product.getFile().getOriginalFilename().equals("")) {
			errors.rejectValue("file", null, "Please select an Image file to Upload");
			return;
		}

		if (!(product.getFile().getContentType().equals("image/jpeg") 
			|| product.getFile().getContentType().equals("image/png"))
			|| product.getFile().getContentType().equals("image/gif")) {
			
			errors.rejectValue("file", null, "Please upload an Image file in the correct format");
			return;

		}
	}

}
