package com.isoft.customersupport.exception;

import com.isoft.customersupport.config.ApplicationException;

public class UserNotFoundException extends ApplicationException {
	public UserNotFoundException(){
		super("User Not Found");
	}
}
