package com.isoft.customersupport.ticket;

import com.isoft.customersupport.location.CustomerLocation;
import com.isoft.customersupport.ticket.category.Category;
import org.hibernate.envers.Audited;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Audited @Entity
public class Ticket {
	
	@Id @GeneratedValue (strategy = GenerationType.AUTO)
	Long id;
	
	@NotNull @Email (flags = Pattern.Flag.CASE_INSENSITIVE)
	private String createdBy;
	
	@Email (flags = Pattern.Flag.CASE_INSENSITIVE)
	private String resolvedBy;
	
	@Email (flags = Pattern.Flag.CASE_INSENSITIVE)
	private String copyEmail;
	
	private String phoneNumber;
	
	@NotNull
	private LocalDateTime createdOn;

	private LocalDateTime resolvedOn;
	
	@NotNull
	private String status, issue;
	
	@NotNull
	private Category ticketCategory;

//	@NotNull
	@Embedded
	private CustomerLocation customerLocation;
	
	@ManyToMany
	private Set<Comments> comments;
	
	public Long getId () {
		return id;
	}
	
	public void setId ( Long id ) {
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
	
	public String getStatus () {
		return status;
	}
	
	public void setStatus ( String status ) {
		this.status = status;
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
	
	@Override
	public String toString () {
		return "Ticket{" +
			  "id=" + id +
			  ", createdBy=" + createdBy +
			  ", resolvedBy=" + resolvedBy +
			  ", copyEmail='" + copyEmail + '\'' +
			  ", phoneNumber='" + phoneNumber + '\'' +
			  ", createdOn=" + createdOn +
			  ", resolvedOn=" + resolvedOn +
			  ", status='" + status + '\'' +
			  ", issue='" + issue + '\'' +
			  ", ticketCategory=" + ticketCategory +
			  ", customerLocation=" + customerLocation +
			  ", comments=" + comments +
			  '}';
	}
	
	@Override
	public boolean equals ( Object o ) {
		if ( this == o ) return true;
		if ( o == null || getClass () != o.getClass () ) return false;
		Ticket ticket = ( Ticket ) o;
		return Objects.equals ( id , ticket.id ) &&
			  Objects.equals ( createdBy , ticket.createdBy ) &&
			  Objects.equals ( resolvedBy , ticket.resolvedBy ) &&
			  Objects.equals ( copyEmail , ticket.copyEmail ) &&
			  Objects.equals ( phoneNumber , ticket.phoneNumber ) &&
			  Objects.equals ( createdOn , ticket.createdOn ) &&
			  Objects.equals ( resolvedOn , ticket.resolvedOn ) &&
			  Objects.equals ( status , ticket.status ) &&
			  Objects.equals ( issue , ticket.issue ) &&
			  Objects.equals ( ticketCategory , ticket.ticketCategory ) &&
			  Objects.equals ( customerLocation , ticket.customerLocation ) &&
			  Objects.equals ( comments , ticket.comments );
	}
	
	@Override
	public int hashCode () {
		return Objects.hash ( id , createdBy , resolvedBy , copyEmail , phoneNumber , createdOn , resolvedOn , status
			  , issue , ticketCategory , customerLocation , comments );
	}
}
