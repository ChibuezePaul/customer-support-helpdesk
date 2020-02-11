package com.isoft.customersupport.exception;

public class ValidationException extends ApplicationException {
	public ValidationException (){
		super("Invalid Data Supplied");
	}
}
