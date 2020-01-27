package com.isoft.customersupport.settings;

import org.springframework.beans.factory.annotation.Value;

public class Setting {
	
	@Value("${ticket.type}")
	public String [] ticketType;
	
	@Value("${ticket.class}")
	public String [] ticketClass;
	
	@Value("${ticket.status}")
	public String [] ticketStatus;
	
	@Value("${customer.branch}")
	public String [] customerBranch;
	
	@Value("${customer.city}")
	public String [] customerCity;
	
	@Value("${customer.country}")
	public String [] customerCountry;
	
}
