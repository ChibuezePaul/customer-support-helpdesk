package com.isoft.customersupport.ticket;

import com.isoft.customersupport.AbstractEntity;
import com.isoft.customersupport.location.CustomerLocation;
import com.isoft.customersupport.ticket.category.Category;
import com.isoft.customersupport.ticket.comments.Comments;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Audited @Entity @Data @EqualsAndHashCode(callSuper = true, exclude = "comments")
public class Ticket extends AbstractEntity {
	
	@Email(flags = Pattern.Flag.CASE_INSENSITIVE)
	private String updatedBy;
	
	@Email(flags = Pattern.Flag.CASE_INSENSITIVE)
	private String createdBy;
	
	@Email (flags = Pattern.Flag.CASE_INSENSITIVE)
	private String resolvedBy;
	
	@Email (flags = Pattern.Flag.CASE_INSENSITIVE)
	private String copyEmail;
	
	private String phoneNumber;
	
	private LocalDateTime createdOn;

	private LocalDateTime updatedOn;
	
	@NotBlank
	private String issue, ticketType, subject;
	
	@Enumerated(EnumType.STRING)
	private TicketFlag ticketStatus;
	
  	@Enumerated(EnumType.STRING)
	private TicketFlag priority;
 
	@ManyToOne
	private Category ticketCategory;

	@ManyToOne
	private CustomerLocation customerLocation;
	
	@ManyToMany
	private List< Comments > comments;
	
	private String attachmentName;
}
