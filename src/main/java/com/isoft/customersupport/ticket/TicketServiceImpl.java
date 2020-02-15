package com.isoft.customersupport.ticket;

import com.isoft.customersupport.config.Util;
import com.isoft.customersupport.exception.TicketNotFoundException;
import com.isoft.customersupport.exception.ValidationException;
import com.isoft.customersupport.team.Team;
import com.isoft.customersupport.ticket.mail.TicketMailService;
import com.isoft.customersupport.usermngt.ActiveDirectory;
import com.isoft.customersupport.usermngt.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service @Slf4j
public class TicketServiceImpl implements TicketService {
	
	private final TicketRepository ticketRepository;
	private final SimpleMailMessage template;
	private final TicketMailService ticketMailService;
	
	@Autowired
	public TicketServiceImpl ( TicketRepository ticketRepository , SimpleMailMessage template , TicketMailService ticketMailService ) {
		this.ticketRepository = ticketRepository;
		this.template = template;
		this.ticketMailService = ticketMailService;
	}
	
	@Override
	public Ticket createTicket ( Ticket ticketCmd, MultipartFile attachment ) {
		if(ticketCmd == null) throw new ValidationException ();
		if(!isValidTicketTitle ( ticketCmd.getTicketTitle () )) throw new ValidationException ();
		Ticket newTicket = new Ticket ();
		newTicket.setCreatedBy ( Util.getCurrentUser () );
		newTicket.setCreatedOn ( LocalDateTime.now () );
		newTicket.setSubject ( ticketCmd.getSubject () );
		newTicket.setIssue ( ticketCmd.getIssue () );
		newTicket.setCopyEmail ( ticketCmd.getCopyEmail () );
		newTicket.setPhoneNumber ( ticketCmd.getPhoneNumber () );
		newTicket.setPriority ( ticketCmd.getPriority () );
		newTicket.setTicketStatus ( TicketFlag.OPEN );
		newTicket.setTicketType ( ticketCmd.getTicketType () );
		newTicket.setTicketTitle ( ticketCmd.getTicketTitle () );
		newTicket.setCustomerLocation ( ticketCmd.getCustomerLocation () );
		newTicket.setTicketCategory ( ticketCmd.getTicketCategory () );
		if(!attachment.isEmpty ()) {
			try {
				InputStream in = attachment.getInputStream ();
				File currDir = new File ( "." );
				String path = currDir.getAbsolutePath ();
				FileOutputStream f = new FileOutputStream (
					  path.substring ( 0 , path.length () - 1 ) + attachment.getOriginalFilename () );
				int ch = 0;
				while ( ( ch = in.read () ) != - 1 ) {
					f.write ( ch );
				}
				
				f.flush ();
				f.close ();
				newTicket.setAttachmentName ( attachment.getResource ().getFilename () );
			}
			catch ( Exception e ) {
				e.printStackTrace ();
			}
		}
		ticketRepository.save(newTicket);
		sendTicketMail(newTicket, newTicket.getTicketCategory().getAssignee ().getSupervisor (), newTicket.getTicketCategory().getAssignee (), newTicket.getAttachmentName ());
		return newTicket;
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
		return ticketRepository.findTicketByTicketStatus(TicketFlag.OPEN);
	}
	
	@Override
	public List< Ticket > findAllSeenTicket () {
		return ticketRepository.findTicketByTicketStatus(TicketFlag.SEEN);
	}
	
	@Override
	public void deleteTicket ( Integer id ) {
		ticketRepository.delete ( ticketRepository.findById ( id ).orElseThrow ( TicketNotFoundException::new ) );
	}
	
	@Override
	public void sendTicketMail ( Ticket ticket, User supervisor, Team team, String attachmentFileName ) {
		ticketMailService.sendTicketMessage (
			  ticket.getSubject (),
			  String.format ( Objects.requireNonNull ( template.getText () ) ,ticket.getTicketType (),ticket.getTicketCategory ().getTicketClass (), ticket.getTicketTitle (), ticket.getIssue (), ticket.getCreatedBy (), ticket.getCreatedOn (),
					! ticket.getPhoneNumber ().equals ( "" ) ? ticket.getPhoneNumber () : "N/A", ticket.getPriority (), ticket.getTicketCategory ().getTAT () ),
			  ticket.getCopyEmail (),
			  supervisor.getEmail (),
			  team.getMembers ().stream ().map( ActiveDirectory :: getEmail ).collect ( Collectors.toList () ),
			  attachmentFileName
		);
	}
	
	@Override
	public Ticket setTicketAsSeen ( Integer id ) {
		Ticket ticket = findTicketById ( id );
		if (ticket.getTicketStatus ().equals ( TicketFlag.OPEN ))
			ticket.setTicketStatus ( TicketFlag.SEEN );
		return ticketRepository.save ( ticket );
	}
	
	@Override
	public boolean isValidTicketTitle ( String ticketTitle ) {
		if ( ticketTitle != null ) {
			return Arrays.asList (
				  "ATM – Link down" , "ATM – Not dispensing" , "ATM – Out of Service" , "Cash balance – unable to login to portal"
				  , "Cheque truncation – faulty cheque scanner" , "Cheque truncation – unable to truncate cheque" , "Clearing – Failing"
				  , "Core Banking Application down" , "Core Banking slow" , "Form M – unable to download form" , "File Server – unable to access a shared folder"
				  , "GSS Scanner – Scanner failing" , "Hardware – Faulty Monitor" , "Hardware – Faulty Printer" , "Hardware – Faulty Switch" , "Hardware – Laptop not booting"
				  , "Hardware – Laptop slow" , "Hardware – OS crashed" , "Hardware – Printer not printing" , "Hardware – Router not coming up" , "Leave Portal down"
				  , "Loan Portal – Portal not loading" , "Loan Portal – unable to login" , "Network – Link down" , "Network – slow link" , "Western Union down" , "ATM – Install new OS on ATM"
				  , "ATM – Setup new ATM" , "Cheque Truncation – Request to setup Cheque Truncation" , "Clearing – setup page" , "File server – request to access shared folder"
				  , "Hardware – Fix Printer Toner" , "Hardware – Install Laptop" , "Hardware – Install Printer" , "Hardware – Install Scanner" , "Hardware – Setup Router"
				  , "Leave Portal – create login details" , "Network – Configure Network Device" , "Network – Configure Network Port" , "Network – Request to setup network"
				  , "Outlook – configure outlook" , "Outlook – create personal folder" , "Outlook – create rules" , "Request to bring in a device" , "Request to install OS on Data Centre Device"
				  , "System Support – setup Users" , "Western Union – setup"
			).contains ( ticketTitle.trim () );
		}
		return false;
	}
}
