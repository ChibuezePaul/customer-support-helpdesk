package com.isoft.customersupport.ticket;

import com.isoft.customersupport.team.Team;
import com.isoft.customersupport.usermngt.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TicketService {
	Ticket createTicket(Ticket newTicketCmd, MultipartFile attachment );
	Ticket findTicketById(Integer id);
	Ticket findTicketByCreatorEmail(String email);
	Ticket findTicketByResolverEmail(String email);
	List<Ticket> findAllTicket();
	List<Ticket> findAllOpenTicket();
	List< Ticket > findAllSeenTicket ();
	void deleteTicket(Integer id);
	void sendTicketMail(Ticket ticket, User supervisor, Team team, String attachmentFileName);
	Ticket setTicketAsSeen ( Integer id );
	boolean isValidTicketTitle(String ticketTitle);
}
