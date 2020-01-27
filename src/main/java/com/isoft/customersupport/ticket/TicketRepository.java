package com.isoft.customersupport.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	Optional<Ticket> findTicketByCreatedBy(String email);
	Optional<Ticket> findTicketByResolvedBy(String email);
}
