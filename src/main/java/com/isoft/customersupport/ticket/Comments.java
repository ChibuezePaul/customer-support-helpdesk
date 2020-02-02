package com.isoft.customersupport.ticket;

import com.isoft.customersupport.AbstractEntity;
import com.isoft.customersupport.usermngt.User;
import org.hibernate.envers.Audited;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity @Audited
public class Comments extends AbstractEntity {
	
//	@NotNull
	private LocalDateTime createdOn;
	
	@ManyToMany(cascade= CascadeType.ALL)
	private Set<User> createdBy;
	
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
	
	public Set< User > getCreatedBy () {
		return createdBy;
	}
	
	public void setCreatedBy ( Set< User > createdBy ) {
		this.createdBy = createdBy;
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
