package com.isoft.customersupport.ticket;

import com.isoft.customersupport.user.User;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Audited
@Entity
public class Comments {
	
	@Id @GeneratedValue (strategy = GenerationType.AUTO)
	Long id;
	
	@NotNull
	private LocalDateTime createdOn;
	
	@NotNull
	private User createdBy;
}
