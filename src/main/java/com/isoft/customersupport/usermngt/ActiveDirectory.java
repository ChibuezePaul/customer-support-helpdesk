package com.isoft.customersupport.usermngt;

import com.isoft.customersupport.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@EqualsAndHashCode ( callSuper = true )
@Entity @Audited @Data
public class ActiveDirectory extends AbstractEntity {
	
	@Column(unique = true) @Email(flags = Pattern.Flag.CASE_INSENSITIVE)
	private String email;
	
	@NotBlank
	private String password;
	
}
