package com.isoft.customersupport.ticket.comments;

import com.isoft.customersupport.ticket.TicketFlag;

import javax.mail.MessagingException;

public interface CommentsService {
	void createComment(Comments comment, TicketFlag status, Integer ticketId) throws MessagingException;
}
