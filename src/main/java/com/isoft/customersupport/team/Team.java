package com.isoft.customersupport.team;

import com.isoft.customersupport.AbstractEntity;
import com.isoft.customersupport.usermngt.ActiveDirectory;
import com.isoft.customersupport.usermngt.User;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity @Audited
public class Team extends AbstractEntity {
	
	@NotBlank @Column(unique = true)
	private String name;
	
	@ManyToOne(cascade= CascadeType.MERGE)//@@NotBlank Email (flags = Pattern.Flag.CASE_INSENSITIVE)
	private User supervisor;
	
	@ManyToMany //@NotBlank
	private Set< ActiveDirectory > members;
	
	public Integer getId () {
		return id;
	}
	
	public void setId ( Integer id ) {
		this.id = id;
	}
	
	public User getSupervisor () {
		return supervisor;
	}
	
	public void setSupervisor ( User supervisor ) {
		this.supervisor = supervisor;
	}
	
	public Set< ActiveDirectory > getMembers () {
		return members;
	}
	
	public void setMembers ( Set< ActiveDirectory > members ) {
		this.members = members;
	}
	
	public String getName () {
		return name;
	}
	
	public void setName ( String name ) {
		this.name = name;
	}
}