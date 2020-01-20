package com.isoft.customersupport.ticket;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class ModifyTicketCmd {
	
	@NotBlank
	private Long id;
	
	@Email @NotBlank
	private String resolvedBy;
	
	@NotBlank
	private LocalDateTime resolvedOn;
	
	@NotBlank
	private String status, category, comments;
	
//	@NotBlank
//	private String category;
//
//	private Set<String> comments;
	
	
	public Long getId () {
		return id;
	}
	
	public void setId ( Long id ) {
		this.id = id;
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
	
	public String getComments () {
		return comments;
	}
	
	public void setComments ( String comments ) {
		this.comments = comments;
	}
}
