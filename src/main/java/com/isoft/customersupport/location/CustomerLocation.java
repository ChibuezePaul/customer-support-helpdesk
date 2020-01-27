package com.isoft.customersupport.location;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity @Audited
@Embeddable
public class CustomerLocation {
	
	@NotNull @Id @GeneratedValue ( strategy = GenerationType.IDENTITY )
	private Integer id;
	
	@NotBlank
	private String customerBranch, customerCity, customerCountry;
	
	public Integer getId () {
		return id;
	}
	
	public void setId ( Integer id ) {
		this.id = id;
	}
	
	public String getCustomerBranch () {
		return customerBranch;
	}
	
	public void setCustomerBranch ( String customerBranch ) {
		this.customerBranch = customerBranch;
	}
	
	public String getCustomerCity () {
		return customerCity;
	}
	
	public void setCustomerCity ( String customerCity ) {
		this.customerCity = customerCity;
	}
	
	public String getCustomerCountry () {
		return customerCountry;
	}
	
	public void setCustomerCountry ( String customerCountry ) {
		this.customerCountry = customerCountry;
	}
	
	@Override
	public String toString () {
		return "CustomerLocation{" +
			  "id=" + id +
			  ", customerBranch='" + customerBranch + '\'' +
			  ", customerCity='" + customerCity + '\'' +
			  ", customerCountry='" + customerCountry + '\'' +
			  '}';
	}
	
	@Override
	public boolean equals ( Object o ) {
		if ( this == o ) return true;
		if ( o == null || getClass () != o.getClass () ) return false;
		CustomerLocation that = ( CustomerLocation ) o;
		return Objects.equals ( id , that.id ) &&
			  Objects.equals ( customerBranch , that.customerBranch ) &&
			  Objects.equals ( customerCity , that.customerCity ) &&
			  Objects.equals ( customerCountry , that.customerCountry );
	}
	
	@Override
	public int hashCode () {
		return Objects.hash ( id , customerBranch , customerCity , customerCountry );
	}
}
