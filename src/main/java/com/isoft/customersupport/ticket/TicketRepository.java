package com.isoft.customersupport.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	Optional<Ticket> findTicketByCreatedBy(String email);
	Optional<Ticket> findTicketByResolvedBy(String email);
	List< Ticket> findTicketByTicketStatus (TicketFlag flag);
	Set< Ticket> findTicketByCommentsIsNull ();
}
