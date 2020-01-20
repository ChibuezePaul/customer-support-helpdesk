package com.isoft.customersupport.ticket;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class NewTicketCmd {
	
	@Email
	@NotNull
	private String createdBy;
	
	@NotNull
	private LocalDateTime createdOn;
	
	@NotNull
	private String status, category, subject, issue;
	
//	@NotNull
//	private String category;
//
//	@NotNull
//	private String subject;
//
//	@NotNull
//	private String issue;
	
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
}
