package com.isoft.customersupport.team;

import com.isoft.customersupport.user.User;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity @Audited
public class Team {
	@Id @GeneratedValue ( strategy = GenerationType.IDENTITY ) @NotNull
	private Integer id;
	
	@Email (flags = Pattern.Flag.CASE_INSENSITIVE) @NotNull @ManyToOne
	private User supervisor;
	
	@NotBlank @ManyToMany
	private Set<User> members;
	
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
	
	public Set< User > getMembers () {
		return members;
	}
	
	public void setMembers ( Set< User > members ) {
		this.members = members;
	}
}