package com.isoft.customersupport.exception;

public class UserNotFoundException extends ApplicationException {
	public UserNotFoundException(){
		super("User Not Found");
	}
}
