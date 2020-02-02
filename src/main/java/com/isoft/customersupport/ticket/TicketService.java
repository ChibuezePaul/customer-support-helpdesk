package com.isoft.customersupport.ticket;

import com.isoft.customersupport.team.Team;
import com.isoft.customersupport.usermngt.User;

import java.util.List;
import java.util.Set;

public interface TicketService {
	Ticket createTicket(Ticket newTicketCmd);
	Ticket updateTicket(Ticket newTicketCmd);
	Ticket findTicketById(Integer id);
	Ticket findTicketByCreatorEmail(String email);
	Ticket findTicketByResolverEmail(String email);
	List<Ticket> findAllTicket();
	List<Ticket> findAllOpenTicket();
	Set< Ticket > findAllUnseenTicket ();
	void deleteTicket(Integer id);
	
	void sendTicketMail(Ticket ticket, User supervisor, Team team);
}
