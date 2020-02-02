package com.isoft.customersupport.config;

import java.util.List;

public interface TicketMailService {
	void sendNewUserCreatedMessage(String subject, String text, String to);
	void sendTicketMessage(String subject, String text, String cc, String bc, List<String> to);
	void sendTicketMessageWithAttachment(String pathToAttachment, String subject, String text, String cc, String bc, String... to);
}
