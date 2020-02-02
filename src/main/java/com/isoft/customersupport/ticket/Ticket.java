package com.isoft.customersupport.ticket;

import com.isoft.customersupport.AbstractEntity;
import com.isoft.customersupport.location.CustomerLocation;
import com.isoft.customersupport.ticket.category.Category;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Audited @Entity
public class Ticket extends AbstractEntity {
	
	private String createdBy;
	
	@Email (flags = Pattern.Flag.CASE_INSENSITIVE)
	private String resolvedBy;
	
	@Email (flags = Pattern.Flag.CASE_INSENSITIVE)
	private String copyEmail;
	
	private String phoneNumber;
	
	private LocalDateTime createdOn;

	private LocalDateTime resolvedOn;
	
	@NotBlank
	private String issue, ticketType, ticketStatus, subject;
	
  	@Enumerated(EnumType.STRING)
	private TicketPriority priority;
	
	@ManyToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="updated_by", referencedColumnName = "id")
	private Category ticketCategory;

	@ManyToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="updated_by", referencedColumnName = "id")
	private CustomerLocation customerLocation;
	
	@ManyToMany
	private Set<Comments> comments;
	
	public Integer getId () {
		return id;
	}
	
	public void setId ( Integer	 id ) {
		this.id = id;
	}
	
	public String getCreatedBy () {
		return createdBy;
	}
	
	public void setCreatedBy ( String createdBy ) {
		this.createdBy = createdBy;
	}
	
	public LocalDateTime getCreatedOn () {
		return createdOn;
	}
	
	public void setCreatedOn ( LocalDateTime createdOn ) {
		this.createdOn = createdOn;
	}
	
	public String getIssue () {
		return issue;
	}
	
	public void setIssue ( String issue ) {
		this.issue = issue;
	}
	
	public String getResolvedBy () {
		return resolvedBy;
	}
	
	public void setResolvedBy ( String resolvedBy ) {
		this.resolvedBy = resolvedBy;
	}
	
	public LocalDateTime getResolvedOn () {
		return resolvedOn;
	}
	
	public void setResolvedOn ( LocalDateTime resolvedOn ) {
		this.resolvedOn = resolvedOn;
	}
	
	public Set<Comments> getComments () {
		return comments;
	}
	
	public void setComments ( Set<Comments> comments ) {
		this.comments = comments;
	}
	
	public Category getTicketCategory () {
		return ticketCategory;
	}
	
	public void setTicketCategory ( Category ticketCategory ) {
		this.ticketCategory = ticketCategory;
	}
	
	public CustomerLocation getCustomerLocation () {
		return customerLocation;
	}
	
	public void setCustomerLocation ( CustomerLocation customerLocation ) {
		this.customerLocation = customerLocation;
	}
	
	public String getCopyEmail () {
		return copyEmail;
	}
	
	public void setCopyEmail ( String copyEmail ) {
		this.copyEmail = copyEmail;
	}
	
	public String getPhoneNumber () {
		return phoneNumber;
	}
	
	public void setPhoneNumber ( String phoneNumber ) {
		this.phoneNumber = phoneNumber;
	}
	
	public TicketPriority getPriority () {
		return priority;
	}
	
	public void setPriority ( TicketPriority priority ) {
		this.priority = priority;
	}
	
	public String getTicketType () {
		return ticketType;
	}
	
	public void setTicketType ( String ticketType ) {
		this.ticketType = ticketType;
	}
	
	public String getTicketStatus () {
		return ticketStatus;
	}
	
	public void setTicketStatus ( String ticketStatus ) {
		this.ticketStatus = ticketStatus;
	}
	
	public String getSubject () {
		return subject;
	}
	
	public void setSubject ( String subject ) {
		this.subject = subject;
	}
	
	@Override
	public String toString () {
		return "Ticket{" +
			  "createdBy='" + createdBy + '\'' +
			  ", resolvedBy='" + resolvedBy + '\'' +
			  ", copyEmail='" + copyEmail + '\'' +
			  ", phoneNumber='" + phoneNumber + '\'' +
			  ", createdOn=" + createdOn +
			  ", resolvedOn=" + resolvedOn +
			  ", issue='" + issue + '\'' +
			  ", priority='" + priority + '\'' +
			  ", ticketCategory=" + ticketCategory +
			  ", customerLocation=" + customerLocation +
			  ", comments=" + comments +
			  ", id=" + id +
			  "} " + super.toString ();
	}
	
	@Override
	public boolean equals ( Object o ) {
		if ( this == o ) return true;
		if ( o == null || getClass () != o.getClass () ) return false;
		Ticket ticket = ( Ticket ) o;
		return Objects.equals ( createdBy , ticket.createdBy ) &&
			  Objects.equals ( resolvedBy , ticket.resolvedBy ) &&
			  Objects.equals ( copyEmail , ticket.copyEmail ) &&
			  Objects.equals ( phoneNumber , ticket.phoneNumber ) &&
			  Objects.equals ( createdOn , ticket.createdOn ) &&
			  Objects.equals ( resolvedOn , ticket.resolvedOn ) &&
			  Objects.equals ( issue , ticket.issue ) &&
			  Objects.equals ( priority , ticket.priority ) &&
			  Objects.equals ( ticketCategory , ticket.ticketCategory ) &&
			  Objects.equals ( customerLocation , ticket.customerLocation ) &&
			  Objects.equals ( comments , ticket.comments );
	}
	
	@Override
	public int hashCode () {
		return Objects.hash ( createdBy , resolvedBy , copyEmail , phoneNumber , createdOn , resolvedOn ,
			  issue , priority , ticketCategory , customerLocation , comments );
	}
}
