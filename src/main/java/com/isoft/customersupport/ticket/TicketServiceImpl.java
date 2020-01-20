package com.isoft.customersupport.ticket;

import java.util.List;

public class TicketServiceImpl implements TicketService {
	@Override
	public Ticket createTicket ( NewTicketCmd newTicketCmd ) {
		Ticket ticket = new Ticket ();
		ticket.setCategory ( newTicketCmd.getCategory () );
		return null;
	}
	
	@Override
	public Ticket updateTicket ( ModifyTicketCmd newTicketCmd ) {
		return null;
	}
	
	@Override
	public Ticket findTicketById ( Long id ) {
		return null;
	}
	
	@Override
	public Ticket findTicketByCreatorEmail ( String email ) {
		return null;
	}
	
	@Override
	public Ticket findTicketByResolverEmail ( String email ) {
		return null;
	}
	
	@Override
	public List< Ticket > findAllTicket () {
		return null;
	}
	
	@Override
	public void deleteTicket ( Long id ) {
	
	}
}
