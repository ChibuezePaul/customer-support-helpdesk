package com.isoft.customersupport.ticket;

import com.isoft.customersupport.config.TicketMailService;
import com.isoft.customersupport.config.Util;
import com.isoft.customersupport.exception.TicketNotFoundException;
import com.isoft.customersupport.exception.ValidationException;
import com.isoft.customersupport.location.CustomerLocation;
import com.isoft.customersupport.location.CustomerLocationRepository;
import com.isoft.customersupport.team.Team;
import com.isoft.customersupport.ticket.category.Category;
import com.isoft.customersupport.ticket.category.CategoryRepository;
import com.isoft.customersupport.ticket.category.CategoryService;
import com.isoft.customersupport.ticket.category.CategoryServiceImpl;
import com.isoft.customersupport.usermngt.ActiveDirectory;
import com.isoft.customersupport.usermngt.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
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
		newTicket.setCreatedBy ( Util.getCurrentUser ().getEmail () );
		newTicket.setCreatedOn ( LocalDateTime.now () );
		newTicket.setSubject ( ticketCmd.getSubject () );
		newTicket.setIssue ( ticketCmd.getIssue () );
		newTicket.setCopyEmail ( ticketCmd.getCopyEmail () );
		newTicket.setPhoneNumber ( ticketCmd.getPhoneNumber () );
		newTicket.setPriority ( ticketCmd.getPriority () );
//		CustomerLocation customerLocation = new CustomerLocation ();
//		customerLocation.setCustomerCountry ( ticketCmd.getCustomerLocation ().getCustomerCountry () );
//		customerLocation.setCustomerCity ( ticketCmd.getCustomerLocation ().getCustomerCity () );
//		customerLocation.setCustomerBranch ( ticketCmd.getCustomerLocation ().getCustomerBranch () );
//		locationRepository.save(customerLocation);
		newTicket.setTicketStatus ( ticketCmd.getTicketStatus () );
		newTicket.setTicketType ( ticketCmd.getTicketType () );
		newTicket.setCustomerLocation ( ticketCmd.getCustomerLocation () );
//		Category ticketCategory = new Category ();
//		ticketCategory.setTicketClass ( ticketCmd.getTicketCategory ().getTicketClass () );
//		ticketCategory.setAssignee ( ticketCmd.getTicketCategory ().getAssignee () );
//		categoryRepository.save(ticketCategory);
		newTicket.setTicketCategory ( ticketCmd.getTicketCategory () );
		ticketRepository.save(newTicket);
//		Team assignedTeam = categoryService.assignTicketToTeamByTicketClass(ticketCmd.getTicketCategory ().getAssignee ());
//		sendTicketMail(newTicket, newTicket.getTicketCategory().getAssignee ().getSupervisor (), newTicket.getTicketCategory().getAssignee ());
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
		return ticketRepository.findTicketByTicketStatus("Open");
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
