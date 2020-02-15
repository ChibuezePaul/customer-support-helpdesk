package com.isoft.customersupport.usermngt;

import com.isoft.customersupport.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity @Audited @Data
@EqualsAndHashCode ( callSuper = true )
public class User extends AbstractEntity {
	
	@Column(unique = true) @Email(flags = Pattern.Flag.CASE_INSENSITIVE)
	private String email;
	
//	@NotBlank
	private String password;
	
	//@NotBlank
	private String oldPassword;
	
	private Boolean isFirstLogin = true;
	
	@Enumerated(EnumType.STRING)
	private Roles role;
}
