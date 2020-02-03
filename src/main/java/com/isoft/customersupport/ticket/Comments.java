package com.isoft.customersupport.ticket;

import com.isoft.customersupport.AbstractEntity;
import com.isoft.customersupport.usermngt.ActiveDirectory;
import com.isoft.customersupport.usermngt.User;
import org.hibernate.envers.Audited;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity @Audited
public class Comments extends AbstractEntity {
	
	private LocalDateTime createdOn;
	
	@ManyToMany(cascade= CascadeType.ALL)
	private List< ActiveDirectory > createdBy;
	
	String details;
	
	public Integer getId () {
		return id;
	}
	
	public void setId ( Integer id ) {
		this.id = id;
	}
	
	public LocalDateTime getCreatedOn () {
		return createdOn;
	}
	
	public void setCreatedOn ( LocalDateTime createdOn ) {
		this.createdOn = createdOn;
	}
	
	public List< ActiveDirectory > getCreatedBy () {
		return createdBy;
	}
	
	public void setCreatedBy ( List< ActiveDirectory > createdBy ) {
		this.createdBy = createdBy;
	}
	
	public String getDetails () {
		return details;
	}
	
	public void setDetails ( String details ) {
		this.details = details;
	}
	
	@Override
	public String toString () {
		return "Comments{" +
			  "createdOn=" + createdOn +
			  ", createdBy=" + createdBy +
			  "} " + super.toString ();
	}
	
	@Override
	public boolean equals ( Object o ) {
		if ( this == o ) return true;
		if ( o == null || getClass () != o.getClass () ) return false;
		Comments comments = ( Comments ) o;
		return Objects.equals ( createdOn , comments.createdOn ) &&
			  Objects.equals ( createdBy , comments.createdBy );
	}
	
	@Override
	public int hashCode () {
		return Objects.hash ( createdOn , createdBy );
	}
}
