package com.isoft.customersupport.exception;

import com.isoft.customersupport.config.ApplicationException;

public class ValidationException extends ApplicationException {
	public ValidationException (){
		super("Invalid Data Supplied");
	}
}
