package com.isoft.customersupport.user;

import com.isoft.customersupport.ticket.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {
	
	@GetMapping("/")
	public String getIndexPage( Model model ){
		model.addAttribute ( "statuses", Arrays.asList ("open","closed","resolved","rejected") );
		model.addAttribute ( "categories", Arrays.asList ("IT Support","Interpersonal","Personal","Task Challenge") );
		model.addAttribute ( "locations", Arrays.asList ("1st Floor","2nd Floor","3rd Floor","4th Floor") );
		model.addAttribute ( "ticket", new Ticket () );
		return "index";
	}
}
