package com.isoft.customersupport.usermngt;

import com.isoft.customersupport.AbstractEntity;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity @Audited
public class ActiveDirectory extends AbstractEntity {
	
	@Column(unique = true) @NotNull @Email(flags = Pattern.Flag.CASE_INSENSITIVE)
	private String email;
	
	@NotNull @NotBlank
	private String password;
	
	public Integer getId () {
		return id;
	}
	
	public void setId ( Integer id ) {
		this.id = id;
	}
	
	public String getEmail () {
		return email;
	}
	
	public void setEmail ( String email ) {
		this.email = email;
	}
	
	public String getPassword () {
		return password;
	}
	
	public void setPassword ( String password ) {
		this.password = password;
	}
}
