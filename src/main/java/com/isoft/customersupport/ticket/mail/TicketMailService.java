package com.isoft.customersupport.ticket.mail;

import java.util.List;

public interface TicketMailService {
	void sendNewUserCreatedMessage(String subject, String text, String to);
	void sendTicketMessage(String subject, String text, String cc, String bc, List<String> to, String fileName);
	void sendTicketCommentMessage(String subject, String text, String bc, List<String> to);
}