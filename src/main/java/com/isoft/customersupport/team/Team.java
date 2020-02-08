package com.isoft.customersupport.team;

import com.isoft.customersupport.AbstractEntity;
import com.isoft.customersupport.usermngt.ActiveDirectory;
import com.isoft.customersupport.usermngt.User;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity @Audited @Data
@EqualsAndHashCode(exclude = "members", callSuper = true)
public class Team extends AbstractEntity {
	@NotBlank @Column(unique = true)
	private String name;
	
	@ManyToOne
	private User supervisor;
	
	@ManyToMany
	private Set< ActiveDirectory > members;
}