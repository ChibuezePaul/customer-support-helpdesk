package com.isoft.customersupport.ticket.category;

import com.isoft.customersupport.AbstractEntity;
import com.isoft.customersupport.team.Team;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity @Audited
public class Category extends AbstractEntity {
	
//	@NotBlank
	private String ticketClass;
	
	@OneToOne//(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
//	@JoinColumn (name = "team_id")
	private Team assignee;
	
	public Integer getId () {
		return id;
	}
	
	public void setId ( Integer id ) {
		this.id = id;
	}
	
	public String getTicketClass () {
		return ticketClass;
	}
	
	public void setTicketClass ( String ticketClass ) {
		this.ticketClass = ticketClass;
	}
	
	public Team getAssignee () {
		return assignee;
	}
	
	public void setAssignee ( Team assignee ) {
		this.assignee = assignee;
	}
	
	@Override
	public String toString () {
		return "Category{" +
			  ", ticketClass='" + ticketClass + '\'' +
			  ", assignee=" + assignee +
			  ", id=" + id +
			  "} " + super.toString ();
	}
	
	@Override
	public boolean equals ( Object o ) {
		if ( this == o ) return true;
		if ( o == null || getClass () != o.getClass () ) return false;
		Category category = ( Category ) o;
		return Objects.equals ( ticketClass , category.ticketClass ) &&
			  Objects.equals ( assignee , category.assignee );
	}
	
	@Override
	public int hashCode () {
		return Objects.hash (  ticketClass , assignee );
	}
}
