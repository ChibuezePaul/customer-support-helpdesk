package com.isoft.customersupport.ticket.comments;

import com.isoft.customersupport.ticket.mail.TicketMailService;
import com.isoft.customersupport.config.Util;
import com.isoft.customersupport.exception.TicketNotFoundException;
import com.isoft.customersupport.ticket.Ticket;
import com.isoft.customersupport.ticket.TicketFlag;
import com.isoft.customersupport.ticket.TicketRepository;
import com.isoft.customersupport.usermngt.ActiveDirectory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service @Slf4j
public class CommentServiceImpl implements CommentsService {
	
	private final CommentsRepository commentsRepository;
	private final TicketRepository ticketRepository;
	private final TicketMailService ticketMailService;
	
	@Autowired
	public CommentServiceImpl ( CommentsRepository commentsRepository , TicketRepository ticketRepository, TicketMailService ticketMailService ) {
		this.commentsRepository = commentsRepository;
		this.ticketRepository = ticketRepository;
		this.ticketMailService = ticketMailService;
	}
	
	@Override
	public void createComment ( Comments comment, TicketFlag status, Integer ticketId ) {
		Comments newComment = new Comments ();
		newComment.setCreatedBy ( Util.getCurrentUser () );
		newComment.setCreatedOn ( LocalDateTime.now () );
		newComment.setDetails ( comment.getDetails () );
		commentsRepository.save ( newComment );
		Ticket ticketToAddComment = ticketRepository.findById ( ticketId ).orElseThrow ( TicketNotFoundException ::new );
		ticketToAddComment.getComments ().add ( newComment );
		if (status != null && (status.equals ( TicketFlag.CLOSED ) || status.equals ( TicketFlag.RESOLVED ) || status.equals ( TicketFlag.REJECTED )))
			ticketToAddComment.setTicketStatus ( status );
		ticketRepository.save ( ticketToAddComment );
		ticketMailService.sendTicketCommentMessage(
			  "New Note Added To Ticket With Subject : ".concat ( ticketToAddComment.getSubject () ),
			  comment.getDetails (), ticketToAddComment.getCreatedBy (),
			  ticketToAddComment.getTicketCategory ().getAssignee ().getMembers ().stream ().map( ActiveDirectory :: getEmail ).collect ( Collectors.toList () )
		);
	}
}
