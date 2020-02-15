package com.isoft.customersupport.location;

import com.isoft.customersupport.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;

@Entity @Audited @Data @EqualsAndHashCode ( callSuper = true )
public class CustomerLocation extends AbstractEntity {
//	@NotBlank
	private String customerBranch;
	private String customerCity, customerCountry;
}
