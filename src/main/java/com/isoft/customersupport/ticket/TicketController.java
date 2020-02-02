package com.isoft.customersupport.ticket;

import com.isoft.customersupport.config.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	@Autowired
	private Messages messages;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
}
