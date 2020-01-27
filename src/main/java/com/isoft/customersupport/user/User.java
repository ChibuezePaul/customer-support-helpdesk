package com.isoft.customersupport.user;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Audited @Entity
public class User {
	
	@Id @GeneratedValue ( strategy = GenerationType.IDENTITY )
	private Integer id;
	
	@Email(flags = Pattern.Flag.CASE_INSENSITIVE) @NotNull
	private String email;
	
	@NotNull
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
	
	@Override
	public String toString () {
		return "User{" +
			  "id=" + id +
			  ", email='" + email + '\'' +
			  '}';
	}
	
	@Override
	public boolean equals ( Object o ) {
		if ( this == o ) return true;
		if ( o == null || getClass () != o.getClass () ) return false;
		User user = ( User ) o;
		return Objects.equals ( id , user.id ) &&
			  Objects.equals ( email , user.email ) &&
			  Objects.equals ( password , user.password );
	}
	
	@Override
	public int hashCode () {
		return Objects.hash ( id , email , password );
	}
}
