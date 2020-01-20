package com.isoft.customersupport.ticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	Ticket findTicketByCreatedBy(String email);
	Ticket findTicketByResolvedBy(String email);
}
