package com.isoft.customersupport.ticket.comments;

import com.isoft.customersupport.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity @Audited @Data @EqualsAndHashCode(callSuper = true, exclude = "createdBy")
public class Comments extends AbstractEntity {
	
	private LocalDateTime createdOn;
	
	@NotBlank
	private String details;
	
	@Email(flags = Pattern.Flag.CASE_INSENSITIVE)
	private String createdBy;
}
