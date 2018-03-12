package com.mukesh.onlineshopping.exception;

import java.io.Serializable;

public class ProductNotFoundException extends Exception implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	
	public ProductNotFoundException()
	{
		this("Product is not Available!");
	}

	public ProductNotFoundException(String message)
	{
		this.errorMessage = System.currentTimeMillis() + ": "+message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}


}
