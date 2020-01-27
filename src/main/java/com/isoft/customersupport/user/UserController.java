package com.isoft.customersupport.user;

import com.isoft.customersupport.settings.Setting;
import com.isoft.customersupport.ticket.Ticket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {
	
	@Value ("${customer.branch}")
	public String [] customerBranch;
	
	@Value("${ticket.type}")
	public String [] ticketType;
	
	@Value("${ticket.class}")
	public String [] ticketClass;
	
	@Value("${ticket.status}")
	public String [] ticketStatus;
	
	@Value("${customer.city}")
	public String [] customerCity;
	
	@Value("${customer.country}")
	public String [] customerCountry;
	
	@GetMapping("/")
	public String getIndexPage( Model model ){
		model.addAttribute ( "types", ticketType );
		model.addAttribute ( "classes", ticketClass );
		model.addAttribute ( "statuses", ticketStatus );
		model.addAttribute ( "branches", customerBranch );
		model.addAttribute ( "cities", customerCity );
		model.addAttribute ( "countries", customerCountry );
		model.addAttribute ( "unseenTicketCount", 0 );
		model.addAttribute ( "openTicketCount", 0 );
		model.addAttribute ( "allTicketCount", 0 );
		model.addAttribute ( "ticket", new Ticket () );
		return "index";
	}
	
	@GetMapping("/login")
	public String getLogin(){
		return "login";
	}
}