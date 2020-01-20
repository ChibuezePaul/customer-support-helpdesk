package com.isoft.customersupport.ticket;

import java.util.List;

public interface TicketService {
	Ticket createTicket(NewTicketCmd newTicketCmd);
	Ticket updateTicket(ModifyTicketCmd newTicketCmd);
	Ticket findTicketById(Long id);
	Ticket findTicketByCreatorEmail(String email);
	Ticket findTicketByResolverEmail(String email);
	List<Ticket> findAllTicket();
	void deleteTicket(Long id);
}
