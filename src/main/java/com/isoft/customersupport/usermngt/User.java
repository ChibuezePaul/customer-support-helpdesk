package com.isoft.customersupport.usermngt;

import com.isoft.customersupport.AbstractEntity;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity @Audited
public class User extends AbstractEntity {
	
	@Column(unique = true) @Email(flags = Pattern.Flag.CASE_INSENSITIVE) @NotBlank
	private String email;
	
//	@NotBlank
	private String password;
	
//	@Transient //@NotBlank
	private String oldPassword;
	
	private Boolean isFirstLogin = true;
	
	@Enumerated(EnumType.STRING)
	private Roles role;
	
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
	
	public Roles getRole () {
		return role;
	}
	
	public void setRole ( Roles role ) {
		this.role = role;
	}
	
	public Boolean getIsFirstLogin () {
		return isFirstLogin;
	}
	
	public void setIsFirstLogin ( Boolean isFirstLogin ) {
		this.isFirstLogin = isFirstLogin;
	}
	
	public String getOldPassword () {
		return oldPassword;
	}
	
	public void setOldPassword ( String oldPassword ) {
		this.oldPassword = oldPassword;
	}
	
	@Override
	public String toString () {
		return "User{" +
			  "email='" + email + '\'' +
			  ", password='" + password + '\'' +
			  ", isFirstLogin='" + isFirstLogin + '\'' +
			  ", role=" + role +
			  ", id=" + id +
			  "} " + super.toString ();
	}
	
	@Override
	public boolean equals ( Object o ) {
		if ( this == o ) return true;
		if ( o == null || getClass () != o.getClass () ) return false;
		User user = ( User ) o;
		return Objects.equals ( email , user.email ) &&
			  Objects.equals ( password , user.password ) &&
			  Objects.equals ( isFirstLogin , user.isFirstLogin ) &&
			  role == user.role;
	}
	
	@Override
	public int hashCode () {
		return Objects.hash ( email , password , isFirstLogin , role );
	}
}
