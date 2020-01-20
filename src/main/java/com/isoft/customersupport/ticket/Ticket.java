package com.isoft.customersupport.ticket;

import org.hibernate.envers.Audited;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Audited @Entity
public class Ticket {
	
	@Id @GeneratedValue (strategy = GenerationType.AUTO)
	Long id;
	
	@Email @NotNull
	private String createdBy, resolvedBy;
	
//	@Email @NotNull
//	private String resolvedBy;
	
	@NotNull
	private LocalDateTime createdOn, resolvedOn;
	
	@NotNull
	private String status, category, subject, issue, comments;
	
//	@NotNull
//	private String category;
	
//	@NotNull
//	private String subject;
	
//	@NotNull
//	private String issue;
	
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
	
	public String getCategory () {
		return category;
	}
	
	public void setCategory ( String category ) {
		this.category = category;
	}
	
	public String getSubject () {
		return subject;
	}
	
	public void setSubject ( String subject ) {
		this.subject = subject;
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
	
	public String getComments () {
		return comments;
	}
	
	public void setComments ( String comments ) {
		this.comments = comments;
	}
	
	@Override
	public String toString () {
		return "Ticket{" +
			  "id=" + id +
			  ", createdBy='" + createdBy + '\'' +
			  ", resolvedBy='" + resolvedBy + '\'' +
			  ", createdOn=" + createdOn +
			  ", resolvedOn=" + resolvedOn +
			  ", status='" + status + '\'' +
			  ", category='" + category + '\'' +
			  ", subject='" + subject + '\'' +
			  ", issue='" + issue + '\'' +
			  ", comments='" + comments + '\'' +
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
			  Objects.equals ( createdOn , ticket.createdOn ) &&
			  Objects.equals ( resolvedOn , ticket.resolvedOn ) &&
			  Objects.equals ( status , ticket.status ) &&
			  Objects.equals ( category , ticket.category ) &&
			  Objects.equals ( subject , ticket.subject ) &&
			  Objects.equals ( issue , ticket.issue ) &&
			  Objects.equals ( comments , ticket.comments );
	}
	
	@Override
	public int hashCode () {
		return Objects.hash ( id , createdBy , resolvedBy , createdOn , resolvedOn , status , category , subject , issue , comments );
	}
}
