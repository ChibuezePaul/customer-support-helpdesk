package com.isoft.customersupport.location;

import com.isoft.customersupport.AbstractEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity @Audited @Data @EqualsAndHashCode ( callSuper = true )
public class CustomerLocation extends AbstractEntity {
//	@NotBlank
	private String customerBranch;
	private String customerCity, customerCountry;
}
