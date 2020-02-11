package com.isoft.customersupport.ticket.category;

import com.isoft.customersupport.AbstractEntity;
import com.isoft.customersupport.team.Team;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity @Audited @Data
@EqualsAndHashCode(callSuper = true)
public class Category extends AbstractEntity {
	
	@NotBlank
	private String ticketClass;
	
	@ManyToOne
	private Team assignee;
	
	private String TAT = "24 Hours";
}
