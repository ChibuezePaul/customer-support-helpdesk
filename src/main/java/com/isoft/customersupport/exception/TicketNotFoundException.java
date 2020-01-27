package com.isoft.customersupport.exception;

public class TicketNotFoundException extends RuntimeException{
	public TicketNotFoundException(){
		super("Ticket Not Found");
	}
}
