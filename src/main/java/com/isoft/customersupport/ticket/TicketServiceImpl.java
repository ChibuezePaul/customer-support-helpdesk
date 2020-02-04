package com.isoft.customersupport.ticket;

import com.isoft.customersupport.config.TicketMailService;
import com.isoft.customersupport.config.Util;
import com.isoft.customersupport.exception.TicketNotFoundException;
import com.isoft.customersupport.exception.ValidationException;
import com.isoft.customersupport.location.CustomerLocationRepository;
import com.isoft.customersupport.team.Team;
import com.isoft.customersupport.ticket.category.CategoryRepository;
import com.isoft.customersupport.ticket.category.CategoryServiceImpl;
import com.isoft.customersupport.usermngt.ActiveDirectory;
import com.isoft.customersupport.usermngt.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service @Slf4j
public class TicketServiceImpl implements TicketService {
	
	private final TicketRepository ticketRepository;
	public final SimpleMailMessage template;
	private final TicketMailService ticketMailService;
	private final CustomerLocationRepository locationRepository;
	private final CategoryRepository categoryRepository;
	private CategoryServiceImpl categoryService;
	
	@Autowired
	public TicketServiceImpl ( TicketRepository ticketRepository , SimpleMailMessage template , TicketMailService ticketMailService, CustomerLocationRepository locationRepository, CategoryRepository categoryRepository, CategoryServiceImpl categoryService ) {
		this.ticketRepository = ticketRepository;
		this.template = template;
		this.ticketMailService = ticketMailService;
		this.locationRepository = locationRepository;
		this.categoryRepository = categoryRepository;
		this.categoryService = categoryService;
	}
	
	@Override
	public Ticket createTicket ( Ticket ticketCmd ) {
		if(ticketCmd == null) throw new ValidationException ();
		Ticket newTicket = new Ticket ();
		newTicket.setUpdatedBy ( Util.getCurrentUser ().getEmail () );
		newTicket.setCreatedOn ( LocalDateTime.now () );
		newTicket.setSubject ( ticketCmd.getSubject () );
		newTicket.setIssue ( ticketCmd.getIssue () );
		newTicket.setCopyEmail ( ticketCmd.getCopyEmail () );
		newTicket.setPhoneNumber ( ticketCmd.getPhoneNumber () );
		newTicket.setPriority ( ticketCmd.getPriority () );
		newTicket.setTicketStatus ( TicketFlag.OPEN );
		newTicket.setTicketType ( ticketCmd.getTicketType () );
		log.info ( "location {}, category {}",ticketCmd.getCustomerLocation ().toString (), ticketCmd.getTicketCategory ().toString () );
		newTicket.setCustomerLocation ( ticketCmd.getCustomerLocation () );
		newTicket.setTicketCategory ( ticketCmd.getTicketCategory () );
		ticketRepository.save(newTicket);
		log.info ( "ticket {}",newTicket.toString () );
		sendTicketMail(newTicket, newTicket.getTicketCategory().getAssignee ().getSupervisor (), newTicket.getTicketCategory().getAssignee ());
		return newTicket;
	}
	
	@Override
	public Ticket updateTicket ( Ticket newTicketCmd ) {
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
	public List< Ticket > findAllOpenTicket () {
		log.info ( ticketRepository.findAll ().toString () );
		return ticketRepository.findTicketByTicketStatus(TicketFlag.OPEN);
	}
	
	@Override
	public Set< Ticket > findAllUnseenTicket () {
		return ticketRepository.findTicketByCommentsIsNull();
	}
	
	@Override
	public void deleteTicket ( Integer id ) {
		ticketRepository.delete ( ticketRepository.findById ( id ).orElseThrow ( TicketNotFoundException::new ) );
	}
	
	@Override
	public void sendTicketMail ( Ticket ticket, User supervisor, Team team ) {
		ticketMailService.sendTicketMessage ( ticket.getSubject (), String.format ( template.getText (),ticket.getIssue () ), ticket.getCopyEmail (), supervisor.getEmail (), team.getMembers ().stream ().map( ActiveDirectory :: getEmail ).collect ( Collectors.toList () ) );
	}
}
