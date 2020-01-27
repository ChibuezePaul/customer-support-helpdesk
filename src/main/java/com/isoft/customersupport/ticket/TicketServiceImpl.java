package com.isoft.customersupport.ticket;

import com.isoft.customersupport.exception.TicketNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Override
	public Ticket createTicket ( NewTicketCmd newTicketCmd ) {
		Ticket ticket = new Ticket ();
		return null;
	}
	
	@Override
	public Ticket updateTicket ( ModifyTicketCmd newTicketCmd ) {
		Ticket ticket = ticketRepository.findById ( newTicketCmd.getId () ).orElseThrow ( TicketNotFoundException::new );
		return null;
	}
	
	@Override
	public Ticket findTicketById ( Integer id ) {
		return ticketRepository.findById ( id ).orElseThrow ( TicketNotFoundException::new );
	}
	
	@Override
	public Ticket findTicketByCreatorEmail ( String email ) {
		return ticketRepository.findTicketByCreatedBy ( email ).orElseThrow ( TicketNotFoundException::new );
	}
	
	@Override
	public Ticket findTicketByResolverEmail ( String email ) {
		return ticketRepository.findTicketByResolvedBy ( email ).orElseThrow ( TicketNotFoundException::new );
	}
	
	@Override
	public List< Ticket > findAllTicket () {
		return ticketRepository.findAll ();
	}
	
	@Override
	public void deleteTicket ( Integer id ) {
		ticketRepository.delete ( ticketRepository.findById ( id ).orElseThrow ( TicketNotFoundException::new ) );
	}
}
