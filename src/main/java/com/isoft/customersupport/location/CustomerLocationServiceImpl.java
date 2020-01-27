package com.isoft.customersupport.location;

import com.isoft.customersupport.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerLocationServiceImpl implements CustomerLocationService {
	
	@Autowired
	CustomerLocationRepository locationRepository;
	
	@Override
	public CustomerLocation createCustomerLocation ( CustomerLocation location ) {
		if(location == null) throw new ValidationException ();
		
		CustomerLocation newLocation = new CustomerLocation ();
		newLocation.setCustomerBranch ( location.getCustomerBranch () );
		newLocation.setCustomerCity ( location.getCustomerCity () );
		newLocation.setCustomerCountry ( location.getCustomerCountry () );
		return locationRepository.save ( newLocation );
	}
	
	@Override
	public List< CustomerLocation > findAllCustomerLocation () {
		return locationRepository.findAll ();
	}
}
