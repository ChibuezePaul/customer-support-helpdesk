package com.isoft.customersupport.ticket.category;

import com.isoft.customersupport.team.Team;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity @Audited
public class Category {
	
	@NotNull @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private String ticketType, ticketClass;
	
	@NotBlank @ManyToOne
	private Team assignee;
	
	public Integer getId () {
		return id;
	}
	
	public void setId ( Integer id ) {
		this.id = id;
	}
	
	public String getTicketType () {
		return ticketType;
	}
	
	public void setTicketType ( String ticketType ) {
		this.ticketType = ticketType;
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
			  "id=" + id +
			  ", ticketType='" + ticketType + '\'' +
			  ", ticketClass='" + ticketClass + '\'' +
			  ", assignee=" + assignee +
			  '}';
	}
	
	@Override
	public boolean equals ( Object o ) {
		if ( this == o ) return true;
		if ( o == null || getClass () != o.getClass () ) return false;
		Category category = ( Category ) o;
		return Objects.equals ( id , category.id ) &&
			  Objects.equals ( ticketType , category.ticketType ) &&
			  Objects.equals ( ticketClass , category.ticketClass ) &&
			  Objects.equals ( assignee , category.assignee );
	}
	
	@Override
	public int hashCode () {
		return Objects.hash ( id , ticketType , ticketClass , assignee );
	}
}
