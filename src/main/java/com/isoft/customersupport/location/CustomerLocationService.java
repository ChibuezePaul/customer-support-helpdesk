package com.isoft.customersupport.location;

import java.util.List;

public interface CustomerLocationService {
	CustomerLocation createCustomerLocation (CustomerLocation location);
	List<CustomerLocation> findAllCustomerLocation ();
}
