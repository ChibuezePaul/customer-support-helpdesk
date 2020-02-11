package com.isoft.customersupport.exception;

public class TicketNotFoundException extends ApplicationException {
	public TicketNotFoundException(){
		super("Ticket Not Found");
	}
}
