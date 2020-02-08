package com.isoft.customersupport.exception;

import com.isoft.customersupport.config.ApplicationException;

public class TicketNotFoundException extends ApplicationException {
	public TicketNotFoundException(){
		super("Ticket Not Found");
	}
}
