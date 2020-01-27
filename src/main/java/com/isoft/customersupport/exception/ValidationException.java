package com.isoft.customersupport.exception;

public class ValidationException extends RuntimeException{
	public ValidationException (){
		super("Invalid Data Supplied");
	}
}
